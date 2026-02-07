package com.fidelity.mts.service;

import com.fidelity.mts.dto.TransferRequestDto;
import com.fidelity.mts.entity.Account;
import com.fidelity.mts.enums.TransactionStatus;

import java.math.BigDecimal;

public interface TransferService {
    TransactionStatus transfer(TransferRequestDto transferRequest);
    boolean validateTransfer(Account senderAcc, Account recieverAcc, BigDecimal amountToBeDebited);
    TransactionStatus executeTransfer();

}
