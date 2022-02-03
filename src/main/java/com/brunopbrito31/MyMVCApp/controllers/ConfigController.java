package com.brunopbrito31.MyMVCApp.controllers;

import java.util.Optional;

import com.brunopbrito31.MyMVCApp.models.entities.InitialPage;
import com.brunopbrito31.MyMVCApp.models.repositories.InitialPageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private InitialPageRepository initialPageRepository;

    @GetMapping("/initial-page/{id}")
    public ResponseEntity<InitialPage> getConfig(@PathVariable Long id){
        Optional<InitialPage> page = initialPageRepository.findById(id);
        return page.isPresent() ? ResponseEntity.ok().body(page.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping("/alter")
    public ResponseEntity<InitialPage> updateConfig(@RequestBody InitialPage page){
        page = initialPageRepository.save(page);
        return ResponseEntity.ok().body(page);
    }
    
}
