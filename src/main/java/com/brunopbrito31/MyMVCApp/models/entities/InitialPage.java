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
@Entity(name="tb_config_pag_ini")
public class InitialPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_url_img_top")
    private String imgTopUrlName;

    @Column(name = "tit_top")
    private String titleTop;

    @Column(name="tit_are_des")
    private String titleProducts;

    @Column(name="des_are_pro")
    private String descriptionProducts;

    @Column(name="sub_desc_pro")
    private String subdescriptionProducts;

    @Column(name="men_top")
    private String msgTop;

    @Column(name="loc_map")
    private String localizationMap;

    @Column(name="des_map")
    private String mapDescription;

    @Column(name="sob_nos_tex")
    private String about;

    private String slogan;

    @Column(name="sob_nos_url_ima_nom")
    private String aboutImgUrlName;

}
