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
import com.example.demo.App.Repositorio.EntrenadorRepository;
import com.example.demo.App.Entity.Entrenadores;





@Controller
@RequestMapping(value = "entrenadores")
public class EntrenadorWebController {
	@Autowired
    private EntrenadorRepository entrenadorRepository;
	

	  @GetMapping("")
	    public String entrenadoresListTemplate(Model model) {
	        model.addAttribute("entrenadores", entrenadorRepository.findAll());
	        return "entrenadores-list";
	    }

	    @GetMapping("/new")
	    public String entrenadoresNewTemplate(Model model) {
	        model.addAttribute("entrenadores", new Entrenadores());
	        return "entrenadores-form";
	    }

	    @GetMapping("/edit/{id}")
	    public String entrenadoresEditTemplate(@PathVariable("id") String id, Model model) {
	        model.addAttribute("entrenadores",entrenadorRepository.findById(id).orElseThrow(() -> new NotFoundException("jugador no encontrado")));
	        return "entrenadores-edit";
	    }

	    @PostMapping("/save")
	    public String entrenadoresSaveProcess(@ModelAttribute("entrenadores") Entrenadores entrenadores,Model model) {
	    	if (entrenadorRepository.existsByNombre(entrenadores.getNombre())) {
	            model.addAttribute("errorMessage", "El entrenador ya existe.");
	            return "entrenadores-form"; 
	        }
	    	
	    	if (entrenadores.getId().isEmpty()) {
	        	entrenadores.setId(null);
	        }
	        entrenadorRepository.save(entrenadores);
	        return "redirect:/entrenadores";
	    }

	    @PostMapping("/save/edit")
	    public String entrenadoresSaveEditProcess(@ModelAttribute("entrenadores") Entrenadores entrenadores,Model model) {
	    	
	    	if (entrenadores.getId().isEmpty()) {
	        	entrenadores.setId(null);
	        }
	        entrenadorRepository.save(entrenadores);
	        return "redirect:/entrenadores";
	    }
	    
	    @GetMapping("/delete/{id}")
	    public String entrenadoresDeleteProcess(@PathVariable("id") String id) {
	    	entrenadorRepository.deleteById(id);
	        return "redirect:/entrenadores";
	    }
	}
