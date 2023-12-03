package com.example.demo.App.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.App.Entity.Asociaciones;

public interface AsociacionRepository extends MongoRepository<Asociaciones, String>{
	boolean existsByNombre(String nombre);
}
