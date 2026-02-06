package com.fidelity.mts.dto;

import com.fidelity.mts.enums.AccountStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountResponseDto {
    private long id;
    private String holderName;
    private BigDecimal balance;
    private AccountStatus status;
    private int version;
    private LocalDateTime lastUpdated;

    public AccountResponseDto(long id, String holderName, BigDecimal balance, AccountStatus status, int version, LocalDateTime lastUpdated) {
        this.id = id;
        this.holderName = holderName;
        this.balance = balance;
        this.status = status;
        this.version = version;
        this.lastUpdated = lastUpdated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
