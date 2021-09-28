package com.sterp.multitenant.tenant.exceptionhandler;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exception_developer_zone")
public class ExceptionResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "status")
	private int status;
	@Column(name = "message")
	private String message;
	@Column(name = "error")
	private String error;
	@Column(name = "path")
	private String path;
	@Column(name = "timestamp")
	private Timestamp timestamp;

	public int getStatus() {
		return status;
	}

	public ExceptionResponse() {
	}

	public ExceptionResponse(int status, String message, String errorcause, String path, Timestamp timestamp) {
		this.status = status;
		this.message = message;
		this.error = errorcause;
		this.path = path;
		this.timestamp = timestamp;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [id=" + id + ", status=" + status + ", message=" + message + ", error=" + error
				+ ", path=" + path + ", timestamp=" + timestamp + "]";
	}

}
