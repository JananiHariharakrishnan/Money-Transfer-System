package com.fidelity.mts.controller;

import com.fidelity.mts.entity.Account;
import com.fidelity.mts.entity.TransactionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fidelity.mts.service.AccountService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class AccountController {
    @Autowired
    AccountService service;

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Account e) {
        long id = service.addAccount(e);
        return ResponseEntity.status(HttpStatus.OK).body("Account with id "+ id +" has been created");
    }
    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccountsById(@PathVariable long id) {
        System.out.println(id);
        Account e = service.getAccount(id);

        return ResponseEntity.status(HttpStatus.OK).body(e);
    }

    @GetMapping("/accounts/{id}/balance")
    public ResponseEntity<BigDecimal> getAccountBalanceById(@PathVariable long id) {
        BigDecimal e = service.getBalance(id);
        return ResponseEntity.status(HttpStatus.OK).body(e);
    }

    @GetMapping("/accounts/{id}/transactions")
    public ResponseEntity<List<TransactionLog>> getTransactionLogById(@PathVariable long id) {
        List<TransactionLog> e = service.getTransactions(id);
        return ResponseEntity.status(HttpStatus.OK).body(e);
    }

}
