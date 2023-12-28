package com.synergisticit.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter

public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long customerId;
	
	
	private String customerName;
	
//	@NotEmpty
//	private String customerGender;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate customerDOB;
	
	private String customerMobileNo;
	
	@Embedded
	private Address customerAddress;
	
	private String customerRealId;
	
	@OneToMany(mappedBy = "accountCustomer")
	private List<Account> customerAccount = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name = "userId")
	private User user;
	
	
	
}
