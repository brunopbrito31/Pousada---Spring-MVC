package com.brunopbrito31.MyMVCApp.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import com.brunopbrito31.MyMVCApp.models.auxiliar.UploadAndDownloadTools;
import com.brunopbrito31.MyMVCApp.models.entities.Image;
import com.brunopbrito31.MyMVCApp.models.exceptions.UploadException;
import com.brunopbrito31.MyMVCApp.models.repositories.ImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@Controller
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping
    public ResponseEntity<Image> uploadImage(
        @RequestParam(name="file") MultipartFile file, 
        @RequestParam(name="nameArq") String name, 
        @RequestParam(name="title") String title,
        HttpServletRequest request
    ) throws URISyntaxException, IOException{
      
        Image imageTemp = Image.builder().nameArq(name).title(title).build();

        try{
            imageTemp = imageRepository.save(imageTemp);
            UploadAndDownloadTools.uploadFile(
                file, 
                UploadAndDownloadTools.getDirUploadImage(), 
                imageTemp.getNameArq()
            );

        }catch(RuntimeException e){
            if(imageTemp.getId().getClass().equals(Long.class)){
                imageRepository.deleteById(imageTemp.getId());
            }
            throw new UploadException(e.getMessage());
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(imageTemp.getId()).toUri();

        return ResponseEntity.created(uri).body(imageTemp);
    }
    
}
