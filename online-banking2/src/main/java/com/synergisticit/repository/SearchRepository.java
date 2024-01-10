package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergisticit.domain.Search;

public interface SearchRepository extends JpaRepository<Search, Long> {

}
