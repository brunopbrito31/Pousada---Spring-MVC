package com.brunopbrito31.MyMVCApp.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="tb_enderecos")
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="complemento")
    private String complement;

    @Column(name="numero")
    private String number;

    @Column(name="CEP", nullable = false)
    private String zipCode;
    
    @Column(name="logradouro")
    private String street;

    @Column(name="bairro", nullable = false)
    private String district;
    
    @Column(name="cidade", nullable = false)
    private String city;

    @Column(name="estado", nullable = false)
    private String state;

    @Column(name="pais", nullable = false)
    private String country;

}
