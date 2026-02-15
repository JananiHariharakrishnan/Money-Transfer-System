package com.fidelity.mts.dto;

import com.fidelity.mts.enums.TransactionStatus;

import java.math.BigDecimal;

public class TransferResponseDto {
    private String id;

    private String finalMessage;

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    private TransactionStatus status;
    private long toAccountId;
    private long fromAccountId;

    private BigDecimal amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFinalMessage() {
        return finalMessage;
    }

    public void setFinalMessage(String finalMessage) {
        this.finalMessage = finalMessage;
    }

    public long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransferResponseDto(String id, String finalMessage, TransactionStatus status, long toAccountId, long fromAccountId, BigDecimal amount) {
        this.id = id;
        this.finalMessage = finalMessage;
        this.status = status;
        this.toAccountId = toAccountId;
        this.fromAccountId = fromAccountId;
        this.amount = amount;
    }


}
