package com.milena.mongodb.relacionamentos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.milena.mongodb.relacionamentos.model.Curso;

public interface CursoRepository  extends MongoRepository <Curso, String>{

}
