package com.brunopbrito31.MyMVCApp.models.repositories;

import com.brunopbrito31.MyMVCApp.models.entities.InitialPage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InitialPageRepository extends JpaRepository<InitialPage,Long>{
    
}
