package com.sinensia.hexagonal.shared.domain.exception;

public class BusinessException extends RuntimeException {

	public BusinessException(String message) {
		super(message);
	}

}
