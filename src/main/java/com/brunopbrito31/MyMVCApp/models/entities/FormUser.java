package com.brunopbrito31.MyMVCApp.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormUser {

    private String user;

    private String password;
    
}
