package com.brunopbrito31.MyMVCApp.models.repositories;

import java.util.List;

import com.brunopbrito31.MyMVCApp.models.entities.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

    @Query(
        value = 
        "SELECT tb_contatos.id, "
        +"tb_contatos.mensagem, "
        +"tb_contatos.data_envio, "
        +"tb_contatos.status, "
        +"tb_contatos.usuario_id, "
        +"tb_usuarios.data_nascimento, "
        +"tb_usuarios.sexo, "
        +"tb_usuarios.email, "
        +"tb_usuarios.nome, "
        +"tb_usuarios.senha, "
        +"tb_usuarios.nivel_autorizacao, "
        +"tb_usuarios.endereco_id, "
        +"tb_enderecos.cidade, "
        +"tb_enderecos.complemento, "
        +"tb_enderecos.pais, "
        +"tb_enderecos.bairro, "
        +"tb_enderecos.numero, "
        +"tb_enderecos.estado, "
        +"tb_enderecos.logradouro, "
        +"tb_enderecos.cep, "
        +"tb_telefones.id, "
        +"tb_telefones.cod_area, "
        +"tb_telefones.numero "
        +"from tb_contatos "
        +"inner join tb_usuarios on tb_contatos.usuario_id = tb_usuarios.id "
        +"inner join tb_enderecos on tb_usuarios.endereco_id = tb_enderecos.id "
        +"inner join tb_telefones on tb_telefones.usuario_id  = tb_usuarios.id "
        +"where tb_contatos.status != 2 order by data_envio ",
        nativeQuery = true
    )
    List<Contact> findAllActiveContacts();
    
}
