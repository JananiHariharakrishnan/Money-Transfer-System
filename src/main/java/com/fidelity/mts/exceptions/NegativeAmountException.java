package com.fidelity.mts.exceptions;

import java.io.Serial;

public class NegativeAmountException extends RuntimeException {
	/**
	 *
	 */
	@Serial
	private static final long serialVersionUID = 1L;

	public NegativeAmountException(String message) {
		super(message);	
	}
}


