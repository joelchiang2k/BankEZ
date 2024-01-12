package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.BankTransaction;
import com.synergisticit.repository.BankTransactionRepository;

@Service
public class BankTransactionServiceImpl implements BankTransactionService {
	
	@Autowired BankTransactionRepository bankTransactionRepository;
	@Override
	public BankTransaction save(BankTransaction bankTransaction) {
		
		return bankTransactionRepository.save(bankTransaction);
	}

	@Override
	public BankTransaction findById(Long bankTransactionId) {
		Optional<BankTransaction> optBankTransaction = bankTransactionRepository.findById(bankTransactionId);
		if(optBankTransaction.isPresent()) {
			return optBankTransaction.get();
		}
		return null;
	}

	@Override
	public List<BankTransaction> findAll() {
		return bankTransactionRepository.findAll();
	}

	@Override
	public void deleteById(Long bankTransactionId) {
		bankTransactionRepository.deleteById(bankTransactionId);
	}
	
	@Override
	public boolean existsById(Long bankTransactionId) {
		return bankTransactionRepository.existsById(bankTransactionId);
	}
}
