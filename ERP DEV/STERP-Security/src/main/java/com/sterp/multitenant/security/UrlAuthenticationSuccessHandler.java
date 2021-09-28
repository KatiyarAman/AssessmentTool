package com.sterp.multitenant.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("this is successfull login");
	}

//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//			Authentication authentication) throws IOException, ServletException {
//		//handle(request, response, authentication);
//		loginNotification(authentication, request);
//
//	}
//
//	private void loginNotification(Authentication authentication, HttpServletRequest request) {
//		try {
//			if (authentication.getPrincipal() instanceof User) {
//				deviceService.verifyDevice(((User) authentication.getPrincipal()), request);
//			}
//		} catch (Exception e) {
//			logger.error("An error occurred verifying device or location");
//			throw new RuntimeException(e);
//		}
//	}

}
