package com.brunopbrito31.MyMVCApp.models.entities.enums;

public enum StatusUser {

    INACTIVE(0),
    ACTIVE(1);

    private Integer selectedStatus;

    private StatusUser(Integer status){
        this.selectedStatus = status;
    }

    public int getOption(){
        return selectedStatus;
    }

    public static StatusUser intToStatusUser(Integer value) {
        if( value != 0 && value != 1){
            throw new IllegalArgumentException("Invalid value!");
        }
        for(StatusUser status : StatusUser.values()){
            if(status.getOption() == value){
                return status;
            }
        }
        return null;
    }
    
}
