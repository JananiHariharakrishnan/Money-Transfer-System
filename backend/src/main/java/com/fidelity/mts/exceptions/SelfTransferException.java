package com.fidelity.mts.exceptions;

import java.io.Serial;

public class SelfTransferException extends RuntimeException {
	/**
	 *
	 */
	@Serial
	private static final long serialVersionUID = 1L;

	public SelfTransferException(String message) {
		super(message);	
	}
}


