package com.sterp.multitenant.tenant.dto;

import java.util.List;
import java.util.Map;

public class Mail {

	private String from;
	private List<String> tos;
	private String subject;
	private String content;
	private String documentNo;
	private Map<String, Object> model;

	public Mail(String from, List<String> tos, String subject, String content, Map<String, Object> model) {
		this.from = from;
		this.tos = tos;
		this.subject = subject;
		this.content = content;
		this.model = model;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

	public Mail() {
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<String> getTos() {
		return tos;
	}

	public void setTos(List<String> tos) {
		this.tos = tos;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Mail{" + "from='" + from + '\'' + ", to='" + tos.toString() + '\'' + ", subject='" + subject + '\''
				+ ", content='" + content + '\'' + '}';
	}
}