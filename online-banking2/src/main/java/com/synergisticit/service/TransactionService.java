package com.synergisticit.service;

import org.springframework.stereotype.Service;

import com.synergisticit.domain.TransactionType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Service
public class TransactionService {
	 private double amount;
	 private Long fromAccount;
	 private Long toAccount;
	 @Enumerated(EnumType.STRING)
		private TransactionType bankTransactionType;
	public TransactionType getBankTransactionType() {
		return bankTransactionType;
	}
	public void setBankTransactionType(TransactionType bankTransactionType) {
		this.bankTransactionType = bankTransactionType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Long getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Long fromAccount) {
		this.fromAccount = fromAccount;
	}
	public Long getToAccount() {
		return toAccount;
	}
	public void setToAccount(Long toAccount) {
		this.toAccount = toAccount;
	}
}
