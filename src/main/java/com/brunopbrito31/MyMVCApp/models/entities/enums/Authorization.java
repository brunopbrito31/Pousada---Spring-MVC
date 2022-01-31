package com.brunopbrito31.MyMVCApp.models.entities.enums;

public enum Authorization {

    OPERATIONS(0),
    ADM(1);

    private Integer selectedAutho;

    private Authorization(Integer autho){
        this.selectedAutho = autho;
    }

    public int getOption(){
        return selectedAutho;
    }

    public static Authorization intToAuthorization(Integer value) {
        if( value != 0 && value != 1){
            throw new IllegalArgumentException("Invalid value!");
        }
        for(Authorization autho : Authorization.values()){
            if(autho.getOption() == value){
                return autho;
            }
        }
        return null;
    }
    
}
