package com.brunopbrito31.MyMVCApp.models.repositories;

import java.util.Optional;

import com.brunopbrito31.MyMVCApp.models.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByMail(String mail);
    
}
