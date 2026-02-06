package com.fidelity.mts.service;

import com.fidelity.mts.entity.Account;
import com.fidelity.mts.entity.TransactionLog;
import com.fidelity.mts.exceptions.AccountNotFoundException;
import com.fidelity.mts.repository.AccountRepository;
import com.fidelity.mts.repository.TransactionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    // Correct way: use the interface type for the variable declaration

    @Autowired
    AccountRepository accountRepo;

    @Autowired
    TransactionLogRepository transactionLogRepo;

    @Override
    public Account getAccount(long id) {
        Optional<Account> opt = accountRepo.findById(id);
        if (!opt.isPresent())
            throw new AccountNotFoundException("Account Not Found!");
        return opt.get();
    }


    @Override
    public BigDecimal getBalance(long id) {
        Optional<Account> opt = accountRepo.findById(id);
        if (!opt.isPresent())
            throw new AccountNotFoundException("Account Not Found!");
        return opt.get().getBalance();
    }

    @Override
    public TransactionLog getTransactions(long id) {
        Optional<TransactionLog> opt = transactionLogRepo.findByFromAccountId(id);
        if (!opt.isPresent())
        {
            opt = transactionLogRepo.findByToAccountId(id);
            if (opt.isEmpty())
                throw new AccountNotFoundException("Account Not Found!");
            return opt.get();
        }
        else {
            return opt.get();
        }
    }

}