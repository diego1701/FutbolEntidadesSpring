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
import com.example.demo.App.Repositorio.AsociacionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.App.Entity.Asociaciones;


@RestController
@RequestMapping(value = "/api/asociaciones")

public class AsociacionRestController {
	@Autowired
    private AsociacionRepository asociacionRepository;
	
	 @GetMapping("/")
	    public List<Asociaciones> getAllAsociaciones() {
	        return asociacionRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public Asociaciones getAsociacionesById(@PathVariable String id) {
	        return asociacionRepository.findById(id).orElseThrow(() -> new NotFoundException("Asociacion no encontrada"));
	    }

	    @PostMapping("/")
	    public Asociaciones saveAsociaciones(@RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Asociaciones asociaciones = mapper.convertValue(body,Asociaciones.class);
	        return asociacionRepository.save(asociaciones);
	    }

	    @PutMapping("/{id}")
	    public Asociaciones updateAsociaciones(@PathVariable String id, @RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Asociaciones asociaciones = mapper.convertValue(body, Asociaciones.class);
	        asociaciones.setId(id);
	        return asociacionRepository.save(asociaciones);
	    }

	    @DeleteMapping("/{id}")
	    public Asociaciones deleteAsociaciones(@PathVariable String id) {
	    	Asociaciones asociaciones = asociacionRepository.findById(id).orElseThrow(() -> new NotFoundException("Asociacion no encontrada"));
	    	asociacionRepository.deleteById(id);
	        return asociaciones;
	    }
}
	    