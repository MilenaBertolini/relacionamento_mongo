package com.milena.mongodb.relacionamentos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.milena.mongodb.relacionamentos.model.Perfil;

public interface PerfilRepository extends MongoRepository<Perfil, String>{
    
}
