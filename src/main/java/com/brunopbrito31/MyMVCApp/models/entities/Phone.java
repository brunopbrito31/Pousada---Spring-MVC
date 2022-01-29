package com.brunopbrito31.MyMVCApp.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="tb_telefones")
public class Phone {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="cod_area", length=3, nullable=false)
    private String areaCode;

    @Column(name="numero", length=10, nullable=false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;
}
