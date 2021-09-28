
package com.sterp.multitenant.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.sterp.multitenant.security.oauth2.CustomOAuth2UserService;
import com.sterp.multitenant.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.sterp.multitenant.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.sterp.multitenant.security.oauth2.OAuth2AuthenticationSuccessHandler;

/**
 * @author Navinder Singh
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;

	@Autowired
	private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

	@Autowired
	private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

	@Autowired
	private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

	@Bean
	public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationFilter();
	}

	/*
	 * By default, Spring OAuth2 uses
	 * HttpSessionOAuth2AuthorizationRequestRepository to save the authorization
	 * request. But, since our service is stateless, we can't save it in the
	 * session. We'll save the request in a Base64 encoded cookie instead.
	 */
	@Bean
	public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
		return new HttpCookieOAuth2AuthorizationRequestRepository();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/api/auth/**", "/h2-console/**").permitAll()
//				.antMatchers("/api/product/**").authenticated().and().exceptionHandling()
//				.authenticationEntryPoint(unauthorizedHandler).and().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf()
				.disable().formLogin().disable().httpBasic().disable().exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler).and().authorizeRequests()
				.antMatchers("/", "/api/auth/**", "/h2-console/**", "/error", "/index", "/favicon.ico", "/**/*.png",
						"/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js", "/tenantInfo/**")
				.permitAll().antMatchers("/auth/**", "/oauth2/**").permitAll().antMatchers("/restrictedZone/**")
				.hasRole("ADMIN").antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/**").authenticated()
				.anyRequest().authenticated().and().oauth2Login().authorizationEndpoint()
				.baseUri("/oauth2/authorize").authorizationRequestRepository(cookieAuthorizationRequestRepository())
				.and().redirectionEndpoint().baseUri("/oauth2/callback/*").and().userInfoEndpoint()
				.userService(customOAuth2UserService).and().successHandler(oAuth2AuthenticationSuccessHandler)
				.failureHandler(oAuth2AuthenticationFailureHandler);
		
		// Add our custom Token based authentication filter
		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}

	private static final String[] AUTH_WHITELIST = { "/h2-console/**", "/swagger-resources/**", "/swagger-ui.html",
			"/v2/api-docs", "/webjars/**","/actuator/**" };

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(AUTH_WHITELIST);
	}

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        return encoder;
//    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@SuppressWarnings("rawtypes")
	@Bean
	public FilterRegistrationBean platformCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		CorsConfiguration configAutenticacao = new CorsConfiguration();
		configAutenticacao.setAllowCredentials(true);
		configAutenticacao.addAllowedOrigin("*");
		configAutenticacao.addAllowedOrigin("https://*");
		configAutenticacao.addAllowedHeader("*");
		configAutenticacao.addAllowedHeader("Content-Type");
		configAutenticacao.addAllowedHeader("Accept");
		configAutenticacao.addAllowedMethod("POST");
		configAutenticacao.addAllowedMethod("GET");
		configAutenticacao.addAllowedMethod("DELETE");
		configAutenticacao.addAllowedMethod("PUT");
		configAutenticacao.addAllowedMethod("OPTIONS");
		configAutenticacao.setMaxAge(3600L);
		source.registerCorsConfiguration("/**", configAutenticacao);

		@SuppressWarnings("unchecked")
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(-110);
		return bean;
	}

}
