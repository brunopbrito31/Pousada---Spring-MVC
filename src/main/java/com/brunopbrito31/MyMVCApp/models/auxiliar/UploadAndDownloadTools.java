package com.brunopbrito31.MyMVCApp.models.auxiliar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class UploadAndDownloadTools {

    private static final String DIR_TO_UPLOAD = System.getenv("Pasta_Uploads");

    private static final String DIR_TO_UPLOAD_IMAGE = DIR_TO_UPLOAD+"images\\";

    public static void uploadFile(
        MultipartFile file, 
        String relativePath, 
        String nameFile
    ) throws IOException{

        System.out.println(relativePath);

        // Captures extension of file
        String[] vectTemp = file.getOriginalFilename().split("[.]");
        String extensionFile = vectTemp[1];

        byte[] bytes = file.getBytes();
        Path path = Paths.get(relativePath+nameFile+"."+extensionFile);
        Files.write(path, bytes);
    }

    public static String getDirUpload(){
        return DIR_TO_UPLOAD;
    }

    public static String getDirUploadImage(){
        return DIR_TO_UPLOAD_IMAGE;
    }
    
}
