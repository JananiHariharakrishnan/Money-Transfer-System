package com.fidelity.mts.exceptions;

public class SelfTransferException extends RuntimeException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public SelfTransferException(String message) {
		super(message);	
	}
}


