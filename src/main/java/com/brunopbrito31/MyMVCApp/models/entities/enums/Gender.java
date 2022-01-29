package com.brunopbrito31.MyMVCApp.models.entities.enums;

public enum Gender {

    MALE(0),
    FEMALE(1);

    private Integer selectedGender;

    private Gender(Integer gender){
        this.selectedGender = gender;
    }

    public int getOption(){
        return selectedGender;
    }

    public static Gender intToGender(Integer value) {
        if( value != 0 && value != 1){
            throw new IllegalArgumentException("Invalid value!");
        }
        for(Gender gender : Gender.values()){
            if(gender.getOption() == value){
                return gender;
            }
        }
        return null;
    }
    
}