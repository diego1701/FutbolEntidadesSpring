package com.example.demo.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.App.Exception.NotFoundException;
import com.example.demo.App.Repositorio.JugadorRepository;
import com.example.demo.App.Entity.Jugadores;





@Controller
@RequestMapping(value = "jugadores")
public class JugadorWebControler {
	@Autowired
    private JugadorRepository JugadorRepository;
	

	  @GetMapping("")
	    public String jugadoresListTemplate(Model model) {
	        model.addAttribute("jugadores", JugadorRepository.findAll());
	        return "jugadores-list";
	    }

	    @GetMapping("/new")
	    public String jugadoresNewTemplate(Model model) {
	        model.addAttribute("jugadores", new Jugadores());
	        return "jugadores-form";
	    }

	    @GetMapping("/edit/{id}")
	    public String jugadoresEditTemplate(@PathVariable("id") String id, Model model) {
	        model.addAttribute("jugadores",JugadorRepository.findById(id).orElseThrow(() -> new NotFoundException("jugador no encontrado")));	        
	        return "jugadores-edit";
	    }

	    @PostMapping("/save")
	    public String jugadoresSaveProcess(@ModelAttribute("jugadores") Jugadores jugadores, Model model) {
	            if(JugadorRepository.existsByNombre(jugadores.getNombre())) {
	                model.addAttribute("errorMessage", "El jugador ya existe.");
	                return "jugadores-form";
	    	}
	    	
	    	if (jugadores.getId().isEmpty()) {
	            jugadores.setId(null);
	        
	    	}
	    	
	        JugadorRepository.save(jugadores);
	        return "redirect:/jugadores";
	    	
	    }
	    
	    
	    @PostMapping("/save/edit")
	    public String jugadoresSaveeditProcess(@ModelAttribute("jugadores") Jugadores jugadores, Model model) {
	    	
	    	if (jugadores.getId().isEmpty()) {
	            jugadores.setId(null);
	        
	    	}
	    	
	        JugadorRepository.save(jugadores);
	        return "redirect:/jugadores";
	    	
	    }
	    
	    @GetMapping("/delete/{id}")
	    public String jugadoresDeleteProcess(@PathVariable("id") String id) {
	    	JugadorRepository.deleteById(id);
	        return "redirect:/jugadores";
	    }
	}

