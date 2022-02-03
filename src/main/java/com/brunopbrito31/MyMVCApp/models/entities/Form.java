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

    private String street;

    private String district;

    private String city;

    private String state;

    private String message;

    @Override
    public String toString(){
        return "name: "+name+" mail: "+mail+" gender: "+gender+" birth: "+birthDate+" phone: "+phone+" zip: "+zipPostal+" street: "+street+" district: "+district+" city: "+city+" state: "+state;
    }

}
