package com.comdata.auto.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.comdata.auto.model.Auto;

public interface AutoDAO extends PagingAndSortingRepository<Auto, UUID>{
	
}
