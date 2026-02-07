package com.fidelity.mts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MtsGlobalNotFoundException {
    @ExceptionHandler(value=AccountNotActiveException.class)
    public ResponseEntity<String> AccountNotActiveException(AccountNotActiveException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value=AccountNotFoundException.class)
    public ResponseEntity<String> AccountNotFoundException(AccountNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value=DuplicateTransferException.class)
    public ResponseEntity<String> DuplicateTransferException(DuplicateTransferException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value=InsufficientBalanceException.class)
    public ResponseEntity<String> InsufficientBalanceException(InsufficientBalanceException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value=SelfTransferException.class)
    public ResponseEntity<String> SelfTransferException(SelfTransferException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value=NegativeAmountException.class)
    public ResponseEntity<String> NegativeBalanceException(NegativeAmountException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
