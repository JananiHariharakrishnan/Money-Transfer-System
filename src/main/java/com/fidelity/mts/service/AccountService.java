package com.fidelity.mts.service;

import com.fidelity.mts.entity.Account;
import com.fidelity.mts.entity.TransactionLog;

import java.math.BigDecimal;

public interface AccountService {
    Account getAccount(long id);

    BigDecimal getBalance(long id);

    TransactionLog getTransactions(long id);

}