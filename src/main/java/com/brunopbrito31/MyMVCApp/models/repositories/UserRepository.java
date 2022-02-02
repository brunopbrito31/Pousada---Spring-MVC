package com.brunopbrito31.MyMVCApp.models.repositories;

import java.util.List;
import java.util.Optional;

import com.brunopbrito31.MyMVCApp.models.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByMail(String mail);

    @Query(
        value=
        "select tb_usuarios.id, "
        +"tb_usuarios.nome, "
        +"tb_usuarios.email, "
        +"tb_usuarios.sexo, "
        +"tb_usuarios.data_nascimento, "
        +"tb_usuarios.senha , "
        +"tb_usuarios.nivel_autorizacao , "
        +"tb_usuarios.status_usuario, "
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
        +"from tb_usuarios "
        +"left join tb_enderecos "
        +"on tb_usuarios.endereco_id = tb_enderecos.id "
        +"left join tb_telefones "
        +"on tb_telefones.usuario_id  = tb_usuarios.id "
        +"WHERE tb_usuarios.status_usuario =1 "
        +"LIMIT ?1, ?2 "
        ,nativeQuery=true
    )
    List<User> findUsersWithPagination(Integer startLimit, Integer pageSize);
    
}
