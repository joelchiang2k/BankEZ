package com.synergisticit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synergisticit.domain.Search;

@Service
public interface SearchService {
	public Search save(Search search);
    public Search findById(Long searchAccountId);
    public List<Search> findAll();
    public void deleteById(Long searchAccountId);
}
