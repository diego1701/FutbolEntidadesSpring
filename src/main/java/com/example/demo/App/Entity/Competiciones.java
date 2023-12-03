package com.example.demo.App.Entity;



import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Document(collection="competiciones")
public class Competiciones {
	
	@Id
	private String id;
	

    @NotEmpty
    @Indexed(unique = true)
    private String nombre;
    
    
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicial;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaFinal;
	public Competiciones() {
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

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	
    

}
