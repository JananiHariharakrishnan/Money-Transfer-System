package com.fidelity.mts;

import java.time.LocalDateTime;
//import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fidelity.mts.entity.Account;
import com.fidelity.mts.enums.AccountStatus;

@SpringBootApplication
public class MoneyTransferSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyTransferSystemApplication.class, args);
		 
        // Creating sample accounts
        Account account1 = new Account(1001, "Alice", 1500.0, AccountStatus.ACTIVE, 1.0, LocalDateTime.now());
        Account account2 = new Account(1002, "Bob", 2500.0, AccountStatus.LOCKED, 1.0, LocalDateTime.now());
        Account account3 = new Account(1003, "Charlie", 3000.0, AccountStatus.ACTIVE, 1.0, LocalDateTime.now());
        Account account4 = new Account(1004, "David", 1000.0, AccountStatus.CLOSED, 1.0, LocalDateTime.now());
        Account account5 = new Account(1005, "Eve", 2000.0, AccountStatus.ACTIVE, 1.0, LocalDateTime.now());
        Account account6 = new Account(1006, "Frank", 500.0, AccountStatus.LOCKED, 1.0, LocalDateTime.now());
        Account account7 = new Account(1007, "Grace", 0.0, AccountStatus.CLOSED, 1.0, LocalDateTime.now());
	
       Account[] accounts = {account1, account2, account3, account4, account5, account6, account7};
        
        System.out.println(accounts);
	}
	
}
