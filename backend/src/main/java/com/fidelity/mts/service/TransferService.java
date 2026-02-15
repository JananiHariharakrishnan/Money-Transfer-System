package com.fidelity.mts.service;

import com.fidelity.mts.dto.TransferRequestDto;
import com.fidelity.mts.dto.TransferResponseDto;
import com.fidelity.mts.entity.Account;

import java.math.BigDecimal;

public interface TransferService {
    TransferResponseDto transfer(TransferRequestDto transferRequest);
    boolean validateTransfer(Account senderAcc, Account recieverAcc, BigDecimal amountToBeDebited,String idempotency_key);

    TransferResponseDto executeTransfer(Account senderAcc, Account recieverAcc, BigDecimal amountToBeDebited, String idempotency_key);

}
