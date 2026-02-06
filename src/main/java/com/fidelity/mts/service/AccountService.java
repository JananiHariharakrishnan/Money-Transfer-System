package com.fidelity.mts.service;

import com.fidelity.mts.entity.Account;
import com.fidelity.mts.entity.TransactionLog;

public interface AccountService {
    Account getAccount(long id);

    double getBalance(long id);

    TransactionLog getTransactions(long id);

}
