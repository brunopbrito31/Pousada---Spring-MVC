package com.brunopbrito31.MyMVCApp.controllers;

import java.util.Optional;

import com.brunopbrito31.MyMVCApp.models.entities.User;
import com.brunopbrito31.MyMVCApp.models.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<User> findUserById(@RequestParam("id") Long id){
        Optional<User> searchedUser = userRepository.findById(id);
        return searchedUser.isPresent() ? 
            ResponseEntity.ok().build() : 
            ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<User> findUserByMail(@RequestParam("mail") String mail){
        Optional<User> searchedUser = userRepository.findByMail(mail);
        return searchedUser.isPresent() ? 
            ResponseEntity.ok().build() : 
            ResponseEntity.noContent().build();
    }
    
}
