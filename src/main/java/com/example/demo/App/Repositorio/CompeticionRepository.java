package com.example.demo.App.Repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.App.Entity.Competiciones;

public interface CompeticionRepository extends MongoRepository<Competiciones, String>{
	 boolean existsByNombre(String nombre);
	 List<Competiciones> findByNombre(String nombre);
	

}