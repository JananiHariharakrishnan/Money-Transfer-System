package com.fidelity.mts.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fidelity.mts.enums.AccountStatus;
import jakarta.persistence.*;

@Entity
@Table(name="accounts")
public class Account{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
	@Column(columnDefinition = "BIGINT",name = "id")
	private long id;

	@Column(name = "holder_name", columnDefinition = "VARCHAR(255)",nullable = false)
	private String holderName;
	@Column(precision = 18, scale = 2, nullable = false)
	private BigDecimal balance;
	@Column(columnDefinition = "VARCHAR(20)",nullable = false)
	private AccountStatus status;
	@Column(columnDefinition = "INT DEFAULT 0")
	private int version;

	@Column(name = "last_updated",columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime lastUpdated;
	public Account() {
	}

	public Account(long id, String holderName, double balance, AccountStatus status, double version,
				   LocalDateTime lastUpdated) {
		this.id = id;
		this.holderName = holderName;
		this.balance = BigDecimal.valueOf(balance);
		this.status = status;
		this.version = (int) version;
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

	public void setBalance(double balance) {
		this.balance = BigDecimal.valueOf(balance);
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public double getVersion() {
		return version;
	}

	public void setVersion(double version) {
		this.version = (int) version;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public double debit(double current_bal, double debit_amount) {
		return current_bal - debit_amount;
	}

	public double credit(double current_bal,double credit_amount) {
		return current_bal + credit_amount;
	}

	public boolean isActive() {
		return status == AccountStatus.ACTIVE;
	}
}