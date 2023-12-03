package com.example.demo.App.Entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import jakarta.validation.constraints.NotEmpty;



@Document(collection="clubes")
public class Clubes {
	
	@Id
	private String id;
	
	@NotEmpty
	private String nombre;
	
	@DocumentReference
	private List<Jugadores> jugadores;
	
	@DocumentReference
	private Asociaciones asociaciones;
	
	@DocumentReference
	private List<Competiciones> competiciones;
	
	@DocumentReference
	private Entrenadores entrenadores;

	public Clubes() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Jugadores> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugadores> jugadores) {
		this.jugadores = jugadores;
	}

	public Asociaciones getAsociaciones() {
		return asociaciones;
	}

	public void setAsociaciones(Asociaciones asociaciones) {
		this.asociaciones = asociaciones;
	}

	public List<Competiciones> getCompeticiones() {
		return competiciones;
	}

	public void setCompeticiones(List<Competiciones> competiciones) {
		this.competiciones = competiciones;
	}

	public Entrenadores getEntrenadores() {
		return entrenadores;
	}

	public void setEntrenadores(Entrenadores entrenadores) {
		this.entrenadores = entrenadores;
	}

	
	
	
	

}
