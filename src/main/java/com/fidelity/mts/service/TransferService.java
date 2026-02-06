package com.fidelity.mts.service;

import com.fidelity.mts.dto.TransferRequestDto;
import com.fidelity.mts.entity.Account;

import java.math.BigDecimal;

public interface TransferService {
    void transfer(TransferRequestDto transferRequest);
    boolean validateTransfer(Account senderAcc, Account recieverAcc, BigDecimal amountToBeDebited);
    void executeTransfer();

}
