package com.fidelity.mts.exceptions;

import java.io.Serial;

public class DuplicateTransferException extends RuntimeException {
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;

	public DuplicateTransferException(String message) {
		super(message);
	}
}

