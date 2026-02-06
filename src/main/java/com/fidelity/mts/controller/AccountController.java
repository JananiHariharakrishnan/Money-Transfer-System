package com.fidelity.mts.controller;

import com.fidelity.mts.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fidelity.mts.service.AccountService;
@RestController
@RequestMapping("api/v1/")
public class AccountController {
    @Autowired
    AccountService service;
    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getEmployeeById(@PathVariable long id) {
        Account e = service.getAccount(id);
        return ResponseEntity.status(HttpStatus.OK).body(e);
    }

}
