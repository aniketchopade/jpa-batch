package com.bookstore.repository;

import com.bookstore.entity.GeneralInfo;
import com.bookstore.impl.BatchRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface GeneralInfoRepository extends BatchRepository<GeneralInfo, Long> {       
    
}

