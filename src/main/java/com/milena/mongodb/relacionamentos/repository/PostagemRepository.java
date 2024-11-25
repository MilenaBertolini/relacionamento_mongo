package com.milena.mongodb.relacionamentos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.milena.mongodb.relacionamentos.model.Postagem;

public interface PostagemRepository extends MongoRepository<Postagem, String>{
    
}
