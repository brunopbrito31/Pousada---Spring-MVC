package com.brunopbrito31.MyMVCApp.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.brunopbrito31.MyMVCApp.models.auxiliar.UploadAndDownloadTools;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Controller
@RequestMapping("/upload/file")
public class UploadController {

    @PostMapping
    public String uploadToDirectory(@RequestParam MultipartFile file) throws IOException {

        byte[] bytes = file.getBytes();
        Path path = Paths.get(UploadAndDownloadTools.getDirUpload()+file.getOriginalFilename());
        Files.write(path, bytes);
        
        return "File: "+file.getOriginalFilename()+", bytes";
    }

    @PostMapping("/images")
    public String uploadImageToDirectory(@RequestParam(name="file") MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UploadAndDownloadTools.getDirUploadImage()+file.getOriginalFilename());
        Files.write(path, bytes);
        System.out.println("saiu de upload");
        return "File: "+file.getOriginalFilename();
    }
    
}
