package com.spring.web.mvc.controller.model;

public class ApplicationResponse {
	private String status;
	private String message;
	private String path;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "ApplicationResponse [status=" + status + ", message=" + message + ", path=" + path + "]";
	}

}
