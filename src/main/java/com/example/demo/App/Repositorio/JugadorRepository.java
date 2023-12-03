package com.example.demo.App.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.App.Entity.Jugadores;


public interface JugadorRepository extends MongoRepository<Jugadores, String>{
	boolean existsByNombre(String nombre);
	 Jugadores findByNombre(String nombre);
	void deleteByNombre(String nombre);
	
}
	
