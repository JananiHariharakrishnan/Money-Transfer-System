package com.fidelity.mts.exceptions;


import java.io.Serial;

public class AccountNotActiveException extends RuntimeException {
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;

	public AccountNotActiveException(String message) {
		super(message);	
	}
}


