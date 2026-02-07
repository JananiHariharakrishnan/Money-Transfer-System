package com.fidelity.mts.service;

import com.fidelity.mts.entity.Account;
import com.fidelity.mts.entity.TransactionLog;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account getAccount(long id);

    BigDecimal getBalance(long id);

    List<TransactionLog> getTransactions(long id);

}