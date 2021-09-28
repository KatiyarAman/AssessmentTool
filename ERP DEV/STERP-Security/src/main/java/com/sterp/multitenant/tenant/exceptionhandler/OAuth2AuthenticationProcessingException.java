package com.sterp.multitenant.tenant.exceptionhandler;

import org.springframework.security.core.AuthenticationException;

public class OAuth2AuthenticationProcessingException extends AuthenticationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4775359369665975721L;

	public OAuth2AuthenticationProcessingException(String msg, Throwable t) {
		super(msg, t);
	}

	public OAuth2AuthenticationProcessingException(String msg) {
		super(msg);
	}
}
