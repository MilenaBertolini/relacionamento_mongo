package com.milena.mongodb.relacionamentos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.milena.mongodb.relacionamentos.model.Estudante;

public interface EstudanteRepository extends MongoRepository<Estudante, String>{
    
}
