package com.sterp.multitenant.tenant.exceptionhandler;

import java.security.Principal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.GenericJDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sterp.multitenant.tenant.exceptionhandler.service.ExceptionService;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlerController implements ResponseBodyAdvice<Collection<?>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);

	@Autowired
	ExceptionService exceptionService;

	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return Collection.class.isAssignableFrom(returnType.getParameterType());
	}

	@Override
	public Collection<?> beforeBodyWrite(Collection<?> body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		response.getHeaders().add("X-Total-Count", String.valueOf(body.size()));
		return body;
	}

	@ExceptionHandler({ AccessDeniedException.class })
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseEntity<?> accessDeniedException(AccessDeniedException deniedException, HttpServletRequest request) {
		ExceptionResponse ex = new ExceptionResponse();
		ex.setStatus(HttpStatus.UNAUTHORIZED.value());
		ex.setPath(request.getRequestURI());
		ex.setError("ACCESS_DENIED");
		ex.setMessage(deniedException.getMessage());
		ex.setTimestamp(new Timestamp(new Date().getTime()));
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", ex);
		String tenantInfo = null;
		if (request.getAttribute("audience") != null) {
			tenantInfo = ((String) request.getAttribute("audience"));
		}
		// exceptionService.storeExceptionDeveloperZone(ex, tenantInfo);
		LOGGER.error("GENERATED LOG FOR TENENT ID: " + tenantInfo, ex);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> dataIntegrityException(DataIntegrityViolationException exception,
			HttpServletRequest request) {
		Map<String, Object> response = new HashMap<String, Object>();
		ExceptionResponse ex = new ExceptionResponse();
		ex.setStatus(HttpStatus.BAD_REQUEST.value());
		ex.setPath(request.getRequestURI());
		ex.setTimestamp(new Timestamp(new Date().getTime()));
		boolean isMatched = false;
		Throwable t = exception.getCause();
		while (t != null) {
			if ((t instanceof DataException)) {
				ex.setError("DATA_MISSING");
				ex.setMessage("Given info is not complete, or missing, Please check given info & try again");
				isMatched = true;
				break;
			}
			if (((t instanceof ConstraintViolationException)) || ((t instanceof PropertyValueException))) {
				ex.setError("DATA_INVALID");
				ex.setMessage(exception.getMessage());
				isMatched = true;
				break;
			}
			t = t.getCause();
		}
		if (!isMatched) {
			ex.setError("BAD_HAPPENED");
			ex.setMessage(exception.getMessage());
		}
		String tenantInfo = null;
		if (request.getAttribute("audience") != null) {
			tenantInfo = ((String) request.getAttribute("audience"));
		}
		response.put("message", ex);
		// exceptionService.storeExceptionDeveloperZone(ex, tenantInfo);
		LOGGER.error("GENERATED LOG FOR TENENT ID: " + tenantInfo, ex);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ CustomException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> handleCustomEx(CustomException exception, HttpServletRequest request) {
		ExceptionResponse ex = new ExceptionResponse();
		ex.setStatus(HttpStatus.BAD_REQUEST.value());
		ex.setPath(request.getRequestURI());
		ex.setError("BAD_REQUEST");
		ex.setMessage(exception.getMessage());
		ex.setTimestamp(new Timestamp(new Date().getTime()));
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", ex);
		String tenantInfo = null;
		if (request.getAttribute("audience") != null) {
			tenantInfo = ((String) request.getAttribute("audience"));
		}
		// exceptionService.storeExceptionDeveloperZone(ex, tenantInfo);
		LOGGER.error("GENERATED LOG", ex);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ ResourceNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException exception,
			HttpServletRequest request) {
		ExceptionResponse ex = new ExceptionResponse();
		ex.setStatus(HttpStatus.NOT_FOUND.value());
		ex.setPath(request.getRequestURI());
		ex.setError("NOT_FOUND");
		ex.setMessage(exception.getMessage());
		ex.setTimestamp(new Timestamp(new Date().getTime()));
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", ex);
		String tenantInfo = null;
		if (request.getAttribute("audience") != null) {
			tenantInfo = ((String) request.getAttribute("audience"));
		}
		// exceptionService.storeExceptionDeveloperZone(ex, tenantInfo);
		LOGGER.error("GENERATED LOG FOR TENENT ID: " + tenantInfo, ex);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ InvalidFormatException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> handleInvalidFormatException(Exception exception,
			HttpServletRequest request, Principal principal) {
		exception.printStackTrace();
		ExceptionResponse ex = new ExceptionResponse();
		ex.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		ex.setPath(request.getRequestURI());
		ex.setError("Invalid data found in the request payload");
		ex.setMessage("Sorry, something went wrong! please try again after some time");
		ex.setTimestamp(new Timestamp(new Date().getTime()));
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", ex);
		String tenantInfo = null;
		if (request.getAttribute("audience") != null) {
			tenantInfo = ((String) request.getAttribute("audience"));
		}
		// exceptionService.storeExceptionDeveloperZone(ex, tenantInfo);
		LOGGER.error("GENERATED LOG FOR TENENT ID: " + tenantInfo, ex);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MultipartException.class)
	@ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE)
	@ResponseBody
	public ResponseEntity<?> handleMultipartException(MultipartException exception, HttpServletRequest request) {
		exception.printStackTrace();
		ExceptionResponse ex = new ExceptionResponse();
		ex.setStatus(HttpStatus.PAYLOAD_TOO_LARGE.value());
		ex.setPath(request.getRequestURI());
		ex.setError("File Size Exceeds");
		ex.setMessage("Please Upload File below 2 MB");
		ex.setTimestamp(new Timestamp(new Date().getTime()));
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", ex);
		String tenantInfo = null;
		if (request.getAttribute("audience") != null) {
			tenantInfo = ((String) request.getAttribute("audience"));
		}
		// exceptionService.storeExceptionDeveloperZone(ex, tenantInfo);
		LOGGER.error("GENERATED LOG FOR TENENT ID: " + tenantInfo, ex);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.PAYLOAD_TOO_LARGE);
	}

	@ExceptionHandler(OutOfMemoryError.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<?> handleMemoryException(OutOfMemoryError exception, HttpServletRequest request) {
		exception.printStackTrace();
		ExceptionResponse ex = new ExceptionResponse();
		ex.setStatus(HttpStatus.BAD_REQUEST.value());
		ex.setPath(request.getRequestURI());
		ex.setError("DATA_INVALID");
		ex.setMessage("Heap Size Exceed");
		ex.setTimestamp(new Timestamp(new Date().getTime()));
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", ex);
		String tenantInfo = null;
		if (request.getAttribute("audience") != null) {
			tenantInfo = ((String) request.getAttribute("audience"));
		}
		// exceptionService.storeExceptionDeveloperZone(ex, tenantInfo);
		LOGGER.error("GENERATED LOG FOR TENENT ID: " + tenantInfo, ex);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(GenericJDBCException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<?> handleSQLException(GenericJDBCException exception, HttpServletRequest request) {
		exception.printStackTrace();
		ExceptionResponse ex = new ExceptionResponse();
		ex.setStatus(HttpStatus.BAD_REQUEST.value());
		ex.setPath(request.getRequestURI());
		ex.setError("Yahhooooooo");
		ex.setMessage(exception.getMessage());
		ex.setTimestamp(new Timestamp(new Date().getTime()));
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", ex);
		String tenantInfo = null;
		if (request.getAttribute("audience") != null) {
			tenantInfo = ((String) request.getAttribute("audience"));
		}
		// exceptionService.storeExceptionDeveloperZone(ex, tenantInfo);
		LOGGER.error("GENERATED LOG FOR TENENT ID: " + tenantInfo, ex);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SQLException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<?> handleSQLException(SQLException exception, HttpServletRequest request) {
		exception.printStackTrace();
		ExceptionResponse ex = new ExceptionResponse();
		ex.setStatus(HttpStatus.BAD_REQUEST.value());
		ex.setPath(request.getRequestURI());
		ex.setError("DATA_INVALID");
		ex.setMessage(exception.getMessage());
		ex.setTimestamp(new Timestamp(new Date().getTime()));
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", ex);
		String tenantInfo = null;
		if (request.getAttribute("audience") != null) {
			tenantInfo = ((String) request.getAttribute("audience"));
		}
		// exceptionService.storeExceptionDeveloperZone(ex, tenantInfo);
		LOGGER.error("GENERATED LOG FOR TENENT ID: " + tenantInfo, ex);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> handleException(Exception exception, HttpServletRequest request,
			Principal principal) {
		exception.printStackTrace();
		ExceptionResponse ex = new ExceptionResponse();
		ex.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		ex.setPath(request.getRequestURI());
		ex.setError("BAD_HAPPENED");
		ex.setMessage("Sorry, something went wrong! please try again after some time");
		ex.setError(exception.getMessage());
		ex.setTimestamp(new Timestamp(new Date().getTime()));
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", ex);
		String tenantInfo = null;
		if (request.getAttribute("audience") != null) {
			tenantInfo = ((String) request.getAttribute("audience"));
		}
		// exceptionService.storeExceptionDeveloperZone(ex, tenantInfo);
		LOGGER.error("GENERATED LOG FOR TENENT ID: " + tenantInfo + "__" + ex, ex);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
