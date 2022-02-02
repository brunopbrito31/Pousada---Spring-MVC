package com.brunopbrito31.MyMVCApp.models.repositories;

import com.brunopbrito31.MyMVCApp.models.entities.UseTerm;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UseTermRepository extends JpaRepository<UseTerm, Long>{
    
}
