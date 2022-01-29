package com.brunopbrito31.MyMVCApp.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Form {

    private String name;

    private String mail;

    private Integer gender;

    private String birthDate;

    private String phone;

    private String zipPostal;

}
