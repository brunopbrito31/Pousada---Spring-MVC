package com.brunopbrito31.MyMVCApp.models.repositories;

import com.brunopbrito31.MyMVCApp.models.entities.Phone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>{
    
}
