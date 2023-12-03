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
import com.example.demo.App.Repositorio.AsociacionRepository;
import com.example.demo.App.Entity.Asociaciones;





@Controller
@RequestMapping(value = "asociaciones")
public class AsociacionWebController {
	@Autowired
    private AsociacionRepository asociacionRepository;
	

	  @GetMapping("")
	    public String asociacionesListTemplate(Model model) {
	        model.addAttribute("asociaciones", asociacionRepository.findAll());
	        return "asociaciones-list";
	    }

	    @GetMapping("/new")
	    public String asociacionesNewTemplate(Model model) {
	        model.addAttribute("asociaciones", new Asociaciones());
	        return "asociaciones-form";
	    }

	    @GetMapping("/edit/{id}")
	    public String asociacionesEditTemplate(@PathVariable("id") String id, Model model) {
	        model.addAttribute("asociaciones",asociacionRepository.findById(id).orElseThrow(() -> new NotFoundException("jugador no encontrado")));
	        return "asociaciones-edit";
	    }

	    @PostMapping("/save")
	    public String asociacionesSaveProcess(@ModelAttribute("asociaciones") Asociaciones asociaciones, Model model) {
	    	if (asociacionRepository.existsByNombre(asociaciones.getNombre())) {
	            model.addAttribute("errorMessage", "La Asociacion ya existe.");
	            return "asociaciones-form"; 
	        }
	    	if (asociaciones.getId().isEmpty()) {
	        	asociaciones.setId(null);
	        }
	        asociacionRepository.save(asociaciones);
	        return "redirect:/asociaciones";
	    }
	    
	    @PostMapping("/save/edit")
	    public String asociacionesSaveEditProcess(@ModelAttribute("asociaciones") Asociaciones asociaciones, Model model) {
	    	
	    	if (asociaciones.getId().isEmpty()) {
	        	asociaciones.setId(null);
	        }
	        asociacionRepository.save(asociaciones);
	        return "redirect:/asociaciones";
	    }


	    @GetMapping("/delete/{id}")
	    public String asociacionesDeleteProcess(@PathVariable("id") String id) {
	    	asociacionRepository.deleteById(id);
	        return "redirect:/asociaciones";
	    }
	}

