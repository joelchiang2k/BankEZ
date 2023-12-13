package com.synergisticit.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class BankTransaction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long bankTransactionId;
	
	private Long bankTransactionFromAccount;
	
	private Long bankTransactionToAccount;
	
	private double bankTransactionAmount;
	
	@Enumerated(EnumType.STRING)
	private TransactionType bankTransactionType;
	
	private LocalDateTime bankTransactionDateTime;
	
	private String comment;
}
