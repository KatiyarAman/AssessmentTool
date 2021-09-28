package com.sterp.multitenant.utils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.security.sasl.AuthenticationException;

import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.sterp.multitenant.constant.JWTConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Navinder Singh
 */
@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public String getAudienceFromToken(String token) {
		return getClaimFromToken(token, Claims::getAudience);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(JWTConstants.SIGNING_KEY).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(String userName, String tenantOrClientId, long defaultUserLoginBranch) {
		return doGenerateToken(userName, tenantOrClientId, defaultUserLoginBranch);
	}

	private String doGenerateToken(String subject, String tenantOrClientId, long defaultUserLoginBranch) {
		Claims claims = Jwts.claims().setSubject(subject).setAudience(tenantOrClientId);
		claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
		claims.put("defaultloginbranch", defaultUserLoginBranch);
		return Jwts.builder().setClaims(claims).setIssuer("system").setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWTConstants.ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
				.signWith(SignatureAlgorithm.HS256, JWTConstants.SIGNING_KEY).compact();
	}

	public String getRefreshToken(String bearerToken, UserDetails userDetails, long defaultUserLoginBranch)
			throws AuthenticationException {
		return dorefreshToken(bearerToken, userDetails, defaultUserLoginBranch);
	}

	private String dorefreshToken(String token, UserDetails userDetails, long defaultUserLoginBranch)
			throws AuthenticationException {
		validateToken(token, userDetails);
		Claims claims = this.getAllClaimsFromToken(token);
		String audience = this.getAudienceFromToken(token);
		if (defaultUserLoginBranch != 0) {
			claims.put("defaultloginbranch", defaultUserLoginBranch);
		}
		if (claims.isEmpty()) {
			throw new AuthorizationServiceException("Invalid token claims");
		}
		return Jwts.builder().setClaims(claims).setAudience(audience).setIssuer("system")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWTConstants.ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
				.signWith(SignatureAlgorithm.HS256, JWTConstants.SIGNING_KEY).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	
	/**
	 * Creates a JWT Token with given details Map and bind it to the given link as query param and
	 * returns it.
	 * */
	public String getTokenLink(Map<String, Object> tokenDetails, String link) {
		final Date expiryDate = tokenDetails.containsKey("expiryDate") ? (Date) tokenDetails.get("expiryDate") : new Date(System.currentTimeMillis() + JWTConstants.ACCESS_TOKEN_VALIDITY_SECONDS * 1000);
		final Date issuedDate = new Date();
		String token = Jwts.builder().setClaims(tokenDetails).setExpiration(expiryDate)
				.setIssuedAt(issuedDate)
				.signWith(SignatureAlgorithm.HS256, JWTConstants.SIGNING_KEY).compact();
		return UriComponentsBuilder.fromUriString(link).queryParam("token", token).build().toString();
	}
	

	
	public String generateTokenForJobPosting(String username, String tenantOrClientId, Long jobPostingId,
			Date expiryDate) {
		Claims claims = Jwts.claims().setSubject(username).setAudience(tenantOrClientId);
		claims.put("jobPostingId", jobPostingId);
		claims.put("registerPageModuleId", "204");
		claims.put("profileCompleteModuleId", "205");
		// get expiry date from Job Profile
		return Jwts.builder().setClaims(claims).setIssuer("Shivit Technologies Pvt. Ltd.").setIssuedAt(new Date())
				.setExpiration(expiryDate).signWith(SignatureAlgorithm.HS256, JWTConstants.SIGNING_KEY).compact();
	}
	
}






