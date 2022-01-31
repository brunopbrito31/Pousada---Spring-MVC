package com.brunopbrito31.MyMVCApp.models.repositories;

import com.brunopbrito31.MyMVCApp.models.entities.CardMenResAre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardMenResAreRepository extends JpaRepository<CardMenResAre, Long>{
    
}
