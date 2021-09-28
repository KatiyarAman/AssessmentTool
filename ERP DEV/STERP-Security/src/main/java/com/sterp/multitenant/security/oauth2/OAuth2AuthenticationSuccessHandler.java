package com.sterp.multitenant.security.oauth2;

import static com.sterp.multitenant.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.sterp.multitenant.constant.AppProperties;
import com.sterp.multitenant.utils.CookieUtils;
import com.sterp.multitenant.utils.JwtTokenUtil;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private JwtTokenUtil tokenProvider;

	private AppProperties appProperties;

	private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

	@Autowired
	HttpSession session;

	@Autowired
	OAuth2AuthenticationSuccessHandler(JwtTokenUtil tokenProvider, AppProperties appProperties,
			HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
		this.tokenProvider = tokenProvider;
		this.appProperties = appProperties;
		this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String targetUrl = determineTargetUrl(request, response, authentication);

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}

		clearAuthenticationAttributes(request, response);
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
				.map(Cookie::getValue);
//		if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
//			throw new BadRequestException(
//					"Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
//		}
		String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
		String tenantId = this.session.getAttribute("tenantOrCientId").toString();
		String username = this.session.getAttribute("username").toString();
		String token = tokenProvider.generateToken(username, tenantId, 1L);
		String redirectUriBuilder = UriComponentsBuilder.fromUriString(targetUrl).queryParam("token", token).build()
				.toUriString();
		return redirectUriBuilder;
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
		super.clearAuthenticationAttributes(request);
		httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
	}

	private boolean isAuthorizedRedirectUri(String uri) {
		URI clientRedirectUri = URI.create(uri);

		return appProperties.getOauth2().getAuthorizedRedirectUris().stream().anyMatch(authorizedRedirectUri -> {
			// Only validate host and port. Let the clients use different paths if they want
			// to
			URI authorizedURI = URI.create(authorizedRedirectUri);
			logger.info("Authorize URI: " + authorizedURI.getHost());
			logger.info("Client URI: " + authorizedURI.getHost());
			if (authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
					&& authorizedURI.getPort() == clientRedirectUri.getPort()) {
				return true;
			}
			return false;
		});
	}
}
