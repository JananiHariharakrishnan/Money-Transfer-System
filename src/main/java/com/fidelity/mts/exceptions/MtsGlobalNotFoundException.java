package com.fidelity.mts.exceptions;

import com.fidelity.mts.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MtsGlobalNotFoundException {
    @ExceptionHandler(value=AccountNotActiveException.class)
    public ResponseEntity<ErrorResponseDto> AccountNotActiveException(AccountNotActiveException ex){
        ErrorResponseDto res = new ErrorResponseDto("ACC-403", ex.getMessage());
        return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value=AccountNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> AccountNotFoundException(AccountNotFoundException ex){
        ErrorResponseDto res = new ErrorResponseDto("ACC-404", ex.getMessage());
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value=DuplicateTransferException.class)
    public ResponseEntity<ErrorResponseDto> DuplicateTransferException(DuplicateTransferException ex){
        ErrorResponseDto res = new ErrorResponseDto("TRX-409", ex.getMessage());
        return new ResponseEntity<>(res, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value=InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponseDto> InsufficientBalanceException(InsufficientBalanceException ex){
        ErrorResponseDto res = new ErrorResponseDto("TRX-400", ex.getMessage());
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value=SelfTransferException.class)
    public ResponseEntity<ErrorResponseDto> SelfTransferException(SelfTransferException ex){
        ErrorResponseDto res = new ErrorResponseDto("VAL-422", ex.getMessage());
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value=NegativeAmountException.class)
    public ResponseEntity<ErrorResponseDto> NegativeBalanceException(NegativeAmountException ex){
        ErrorResponseDto res = new ErrorResponseDto("VAL-422", ex.getMessage());
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
