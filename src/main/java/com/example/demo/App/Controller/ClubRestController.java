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
import com.example.demo.App.Repositorio.ClubRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.App.Entity.Clubes;


@RestController
@RequestMapping(value = "/api/clubes")

public class ClubRestController {
	@Autowired
    private ClubRepository  clubRepository;
	
	 @GetMapping("/")
	    public List<Clubes> getAllClubes() {
	        return  clubRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public Clubes getClubesById(@PathVariable String id) {
	        return  clubRepository.findById(id).orElseThrow(() -> new NotFoundException("Club no encontrado"));
	    }

	    @PostMapping("/")
	    public Clubes saveClubes(@RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Clubes clubes = mapper.convertValue(body,Clubes.class);
	        return  clubRepository.save(clubes);
	    }

	    @PutMapping("/{id}")
	    public Clubes updateClubes(@PathVariable String id, @RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Clubes  clubes = mapper.convertValue(body, Clubes.class);
	        clubes.setId(id);
	        return  clubRepository.save(clubes);
	    }

	    @DeleteMapping("/{id}")
	    public Clubes deleteClubes(@PathVariable String id) {
	    	Clubes  clubes =  clubRepository.findById(id).orElseThrow(() -> new NotFoundException("Club no encontrado"));
	    	 clubRepository.deleteById(id);
	        return  clubes;
	    }
}