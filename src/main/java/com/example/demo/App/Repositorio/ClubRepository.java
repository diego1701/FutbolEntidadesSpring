package com.example.demo.App.Repositorio;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.App.Entity.Clubes;

public interface ClubRepository extends MongoRepository<Clubes, String>{
	boolean existsByNombre(String nombre);
}