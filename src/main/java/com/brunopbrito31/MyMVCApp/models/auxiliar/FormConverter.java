package com.brunopbrito31.MyMVCApp.models.auxiliar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.brunopbrito31.MyMVCApp.models.entities.Form;
import com.brunopbrito31.MyMVCApp.models.entities.Phone;

public class FormConverter {

    public static Phone phoneMounterNoContentUser(Form form){
        String areaCode = form.getPhone().substring(1,3);
        String phonePt1 = form.getPhone().substring(5,10);
        String phonePt2 = form.getPhone().substring(11);
        String phoneRec = phonePt1.concat(phonePt2).trim();
        return Phone.builder().areaCode(areaCode).number(phoneRec).build();
    }

    public static Date StringToDate(String date) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd"); 
        Date formatedDate = format.parse(date); 
        return formatedDate;
    }
    
}
