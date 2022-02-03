package com.brunopbrito31.MyMVCApp.models.repositories;

import java.util.List;

import com.brunopbrito31.MyMVCApp.models.entities.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
        +"left join tb_usuarios on tb_contatos.usuario_id = tb_usuarios.id "
        +"left join tb_enderecos on tb_usuarios.endereco_id = tb_enderecos.id "
        +"left join tb_telefones on tb_telefones.usuario_id  = tb_usuarios.id "
        +"where tb_contatos.status != 2 order by data_envio, status LIMIT :start, :size ",
        nativeQuery = true
    )
    List<Contact> findAllActiveContacts(@Param("start")Integer startLimit, @Param("size") Integer pageSize);
    
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
        +"left join tb_usuarios on tb_contatos.usuario_id = tb_usuarios.id "
        +"left join tb_enderecos on tb_usuarios.endereco_id = tb_enderecos.id "
        +"left join tb_telefones on tb_telefones.usuario_id  = tb_usuarios.id "
        +"where tb_contatos.status != 2 and tb_usuarios.nome LIKE '%:filter%' order by data_envio, status LIMIT :start, :size ",
        nativeQuery = true
    )
    List<Contact> findAllActiveContactsWithFilterNameOrMail(@Param("start")Integer startLimit, @Param("size")Integer pageSize, @Param("filter")String content);

    @Query(
        value = 
        "SELECT COUNT(tb_contatos.id) "
        +"from tb_contatos "
        +"left join tb_usuarios on tb_contatos.usuario_id = tb_usuarios.id "
        +"left join tb_enderecos on tb_usuarios.endereco_id = tb_enderecos.id "
        +"left join tb_telefones on tb_telefones.usuario_id  = tb_usuarios.id "
        +"where tb_contatos.status != 2 and tb_usuarios.nome LIKE '%:filter%' ",
        nativeQuery = true
    )
    Long findAllActiveContactsWithFilterNameOrMailCount(@Param("filter") String content);

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
        +"left join tb_usuarios on tb_contatos.usuario_id = tb_usuarios.id "
        +"left join tb_enderecos on tb_usuarios.endereco_id = tb_enderecos.id "
        +"left join tb_telefones on tb_telefones.usuario_id  = tb_usuarios.id "
        +"where tb_contatos.status = 0 order by data_envio, status LIMIT :start, :size ",
        nativeQuery = true
    )
    List<Contact> findAllActiveOpenContacts(@Param("start")Integer startLimit, @Param("size") Integer pageSize);

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
        +"left join tb_usuarios on tb_contatos.usuario_id = tb_usuarios.id "
        +"left join tb_enderecos on tb_usuarios.endereco_id = tb_enderecos.id "
        +"left join tb_telefones on tb_telefones.usuario_id  = tb_usuarios.id "
        +"where tb_contatos.status = 0 and tb_usuarios.nome LIKE '%:filter%' order by data_envio, status LIMIT :start, :size ",
        nativeQuery = true
    )
    List<Contact> findAllActiveOpenContactsWithFilterByNameOrMail(@Param("start")Integer startLimit, @Param("size")Integer pageSize, @Param("filter") String filter);

    @Query(
        value = 
        "SELECT COUNT(tb_contatos.id) "
        +"from tb_contatos "
        +"left join tb_usuarios on tb_contatos.usuario_id = tb_usuarios.id "
        +"left join tb_enderecos on tb_usuarios.endereco_id = tb_enderecos.id "
        +"left join tb_telefones on tb_telefones.usuario_id  = tb_usuarios.id "
        +"where tb_contatos.status = 0 and tb_usuarios.nome LIKE '%:filter%' ) ",
        nativeQuery = true
    )
    Long findAllActiveOpenContactsWithFilterByNameOrMailCount(@Param("filter") String filter);

    // Personalized Count for Pagination with status filter
    @Query(
        value = 
        "SELECT count(tb_contatos.id) "
        +"from tb_contatos "
        +"left join tb_usuarios on tb_contatos.usuario_id = tb_usuarios.id "
        +"left join tb_enderecos on tb_usuarios.endereco_id = tb_enderecos.id "
        +"left join tb_telefones on tb_telefones.usuario_id  = tb_usuarios.id "
        +"where tb_contatos.status != 2 ",
        nativeQuery = true
    )
    Long persCount ();

    // Personalized Count for Pagination with status filter
    @Query(
        value = 
        "SELECT count(tb_contatos.id) "
        +"from tb_contatos "
        +"left join tb_usuarios on tb_contatos.usuario_id = tb_usuarios.id "
        +"left join tb_enderecos on tb_usuarios.endereco_id = tb_enderecos.id "
        +"left join tb_telefones on tb_telefones.usuario_id  = tb_usuarios.id "
        +"where tb_contatos.status = 0 ",
        nativeQuery = true
    )
    Long persOpenCount ();
    
}
