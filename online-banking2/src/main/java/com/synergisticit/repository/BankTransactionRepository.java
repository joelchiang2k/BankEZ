package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergisticit.domain.BankTransaction;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long> {

}
