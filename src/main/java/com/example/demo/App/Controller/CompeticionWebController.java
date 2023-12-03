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
import com.example.demo.App.Repositorio.CompeticionRepository;
import com.example.demo.App.Entity.Competiciones;


@Controller
@RequestMapping(value = "competiciones")
public class CompeticionWebController {
	@Autowired
    private CompeticionRepository competicionRepository ;
	
	

	  @GetMapping("")
	    public String competicionesListTemplate(Model model) {
	        model.addAttribute("competiciones", competicionRepository.findAll());
	        return "competiciones-list";
	    }

	    @GetMapping("/new")
	    public String competicionesNewTemplate(Model model) {
	        model.addAttribute("competiciones", new Competiciones());
	       
	        
	        return "competiciones-form";
	    }

	    @GetMapping("/edit/{id}")
	    public String competicionesEditTemplate(@PathVariable("id") String id, Model model) {
	    	Competiciones competiciones = competicionRepository.findById(id).orElseThrow(() -> new NotFoundException("Competición no encontrada"));
	    	model.addAttribute("competiciones", competiciones);
	        
	        
	        return "competiciones-edit";
	    }

	    @PostMapping("/save")
	    public String competicionesSaveProcess(@ModelAttribute("competiciones") Competiciones competiciones,Model model) {
	    	if (competicionRepository.existsByNombre(competiciones.getNombre())) {
	            model.addAttribute("errorMessage", "La competición ya existe.");
	            return "competiciones-form"; 
	        }
	    	
	    	if (competiciones.getId().isEmpty()) {
	        	competiciones.setId(null);
	        }
	        competicionRepository.save(competiciones);
	        return "redirect:/competiciones";
	    }
	    

	    @PostMapping("/save/edit")
	    public String competicionesSaveEditProcess(@ModelAttribute("competiciones") Competiciones competiciones,Model model) {
	    
	    	
	    	if (competiciones.getId().isEmpty()) {
	        	competiciones.setId(null);
	        }
	        competicionRepository.save(competiciones);
	        return "redirect:/competiciones";
	    }

	    @GetMapping("/delete/{id}")
	    public String competicionesDeleteProcess(@PathVariable("id") String id) {
	    	competicionRepository.deleteById(id);
	        return "redirect:/competiciones";
	    }
	    
	    
	    
	}

