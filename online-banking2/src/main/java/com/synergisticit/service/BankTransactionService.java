package com.synergisticit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synergisticit.domain.BankTransaction;

@Service
public interface BankTransactionService {
	public BankTransaction save(BankTransaction bankTransaction);
    public BankTransaction findById(Long bankTransactionId);
    public List<BankTransaction> findAll();
    public void deleteById(Long bankTransactionId);
    public boolean existsById(Long bankTransactionId);
}
