package com.brunopbrito31.MyMVCApp.models.entities;

import java.math.BigDecimal;

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
@Entity(name="tb_produtos")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="titulo", nullable = false)
    private String title;

    @Column(name="descricao", nullable= false)
    private String description;

    @Column(name="preco", nullable= false)
    private BigDecimal price;

    @Column(name="caminho_img", nullable= false)
    private String urlImg;

}
