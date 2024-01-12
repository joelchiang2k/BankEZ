package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.Branch;
import com.synergisticit.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired AccountRepository accountRepository;
	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public Account findById(Long accountId) {
		Optional<Account> optAccount = accountRepository.findById(accountId);
		if(optAccount.isPresent()) {
			return optAccount.get();
		}
		return null;
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}

	@Override
	public void deleteById(Long accountId) {
		// TODO Auto-generated method stub
		accountRepository.deleteById(accountId);
	}
	
	@Override
	public boolean existsById(Long accountId) {
		return accountRepository.existsById(accountId);
	}

}
