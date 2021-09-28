package com.sterp.multitenant.tenant.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such User")  // 404
public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -2953991919720286662L;
}
