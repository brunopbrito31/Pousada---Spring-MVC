package com.brunopbrito31.MyMVCApp.models.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.brunopbrito31.MyMVCApp.models.entities.enums.StatusContact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="tb_contatos")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id",referencedColumnName = "id")
    private User user;

    @Column(name="data_envio")
    private Date sendDate;

    @Column(name="mensagem")
    private String msg;

    private StatusContact status;
    
    
}
