package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Search;
import com.synergisticit.repository.SearchRepository;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired SearchRepository searchRepository;
	
	@Override
	public Search save(Search search) {
		return searchRepository.save(search);
	}

	@Override
	public Search findById(Long searchAccountId) {
		Optional<Search> search = searchRepository.findById(searchAccountId);
		if(search.isPresent()) {
			return search.get();
		}
		return null;
	}

	@Override
	public List<Search> findAll() {
		// TODO Auto-generated method stub
		return searchRepository.findAll();
	}

	@Override
	public void deleteById(Long searchAccountId) {
		// TODO Auto-generated method stub
		searchRepository.deleteById(searchAccountId);
	}

}
