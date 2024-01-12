package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Account;

public interface AccountService {
	public Account save(Account account);
    public Account findById(Long accountId);
    public List<Account> findAll();
    public void deleteById(Long accountId);
    boolean existsById(Long accountId);
}
