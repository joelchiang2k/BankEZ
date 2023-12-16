package com.synergisticit.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Getter
@Setter
@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
     private Long userId;
     
	 @NotEmpty
	 @Column(name="name")
    String username;
     
	 @NotEmpty
    String password;
     
	 @Email
	 @NotEmpty
     String email;
     
     @ManyToMany
     @JoinTable(
    	name="user_role",
        joinColumns = { @JoinColumn(name="user_id")},
        inverseJoinColumns = {@JoinColumn(name="role_id")}
    		 
    		 )
     List<Role> roles = new ArrayList<>();
        
}