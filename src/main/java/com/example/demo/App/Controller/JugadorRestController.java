package com.example.demo.App.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.App.Exception.NotFoundException;
import com.example.demo.App.Repositorio.JugadorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.App.Entity.Jugadores;


@RestController
@RequestMapping(value = "/api/jugadores")

public class JugadorRestController {
	@Autowired
    private JugadorRepository jugadorRepository;
	
	 @GetMapping("/")
	    public List<Jugadores> getAllJugadores() {
	        return jugadorRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public Jugadores getJugadoresById(@PathVariable String id) {
	        return jugadorRepository.findById(id).orElseThrow(() -> new NotFoundException("Jugador no encontrado"));
	    }

	    @PostMapping("/")
	    public Jugadores saveJugadores(@RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Jugadores jugadores = mapper.convertValue(body, Jugadores.class);
	        return jugadorRepository.save(jugadores);
	    }

	    @PutMapping("/{id}")
	    public Jugadores updateJugadores(@PathVariable String id, @RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Jugadores jugadores = mapper.convertValue(body, Jugadores.class);
	        jugadores.setId(id);
	        return jugadorRepository.save(jugadores);
	    }

	    @DeleteMapping("/{id}")
	    public Jugadores deleteJugadores(@PathVariable String id) {
	    	Jugadores jugadores = jugadorRepository.findById(id).orElseThrow(() -> new NotFoundException("jugador no encontrado"));
	    	jugadorRepository.deleteById(id);
	        return jugadores;
	    }
}
	    
