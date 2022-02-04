package com.brunopbrito31.MyMVCApp.models.auxiliar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// Classe construida para testar a integração entre as apis externas
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroTemp {

    private Long id;

    private String name;
    
}
