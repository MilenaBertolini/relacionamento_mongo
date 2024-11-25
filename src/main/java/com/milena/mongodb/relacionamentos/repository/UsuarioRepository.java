package com.milena.mongodb.relacionamentos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.milena.mongodb.relacionamentos.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String>{
    
}
