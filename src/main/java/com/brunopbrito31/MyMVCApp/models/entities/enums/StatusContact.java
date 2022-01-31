package com.brunopbrito31.MyMVCApp.models.entities.enums;

public enum StatusContact {
    
    OPENED(0),
    CLOSED(1),
    DELETED(2);

    private Integer selectedStatus;

    private StatusContact(Integer status){
        this.selectedStatus = status;
    }

    public int getOption(){
        return selectedStatus;
    }

    public static StatusContact intToStatusContact(Integer value) {
        if(value < 0 && value >2){
            throw new IllegalArgumentException("Invalid value!");
        }
        for(StatusContact statusCont : StatusContact.values()){
            if(statusCont.getOption() == value){
                return statusCont;
            }
        }
        return null;
    }
}
