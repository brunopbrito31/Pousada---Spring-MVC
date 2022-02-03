package com.brunopbrito31.MyMVCApp.models.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.brunopbrito31.MyMVCApp.models.entities.enums.Authorization;
import com.brunopbrito31.MyMVCApp.models.entities.enums.Gender;
import com.brunopbrito31.MyMVCApp.models.entities.enums.StatusUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="tb_usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "email", nullable = false , unique = true)
    private String mail;

    @Column(name = "sexo", nullable = false)
    private Gender gender;

    @Column(name= "data_nascimento", nullable = false)
    private Date birthDate;

    @Column(name= "senha")
    private String password;

    @Column(name= "nivel_autorizacao")
    private Authorization autho;

    @Column(name= "status_usuario")
    private StatusUser statusUser;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Phone> Phones;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id",referencedColumnName = "id")
    private Adress adress;
    
}
