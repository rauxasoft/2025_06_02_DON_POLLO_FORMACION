package com.sinensia.hexagonal.infrastrucure.adapter.in.restcontroller.config;

public class ErrorResponse {
	
	private String error;

	public ErrorResponse(String error) {
		this.error = error;
	}
	
	public String getError() {
		return error;
	}

}
