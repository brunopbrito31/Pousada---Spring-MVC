package com.brunopbrito31.MyMVCApp.config;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component @EnableScheduling
public class RoutineExecutor {

    @Scheduled(fixedDelay = 5000 )

    //Rotina que imprime uma mensagem para sinalizar que a aplicação está rodando
    public void verifyFunc(){
        System.out.println("application is ok!");
    }
}