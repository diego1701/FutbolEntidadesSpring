package com.example.demo.App.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.App.Entity.Competiciones;
import com.example.demo.App.Repositorio.CompeticionRepository;

@Controller
@RequestMapping(value = "index")
public class MainController {
	@Autowired
    private CompeticionRepository competicionRepository ;
	
	
	
	
	@GetMapping("")
	public String index(Model model) {
	    List<Competiciones> competiciones = competicionRepository.findAll();

	    Date fechaMasCercana = null;
	    Date ahora = new Date();
	    String nombreCompeticion=null;

	    for (Competiciones competicion : competiciones) {
	        Date fechaInicio = competicion.getFechaInicial();
	        

	        if (fechaInicio.after(ahora) && (fechaMasCercana == null || fechaInicio.before(fechaMasCercana))) {
	            fechaMasCercana = fechaInicio;
	            nombreCompeticion = competicion.getNombre();
	        }
	    }
	    
	    

	    if (fechaMasCercana != null) {
	        long diferenciaMilisegundos = fechaMasCercana.getTime() - ahora.getTime();
	        long diasRestantes = diferenciaMilisegundos / (1000 * 60 * 60 * 24);
	        long horasRestantes = diferenciaMilisegundos / (1000 * 60 * 60);
	        long minRestantes = diferenciaMilisegundos / (1000 * 60);
	        model.addAttribute("diasRestantes", diasRestantes);
	        model.addAttribute("horasRestantes", horasRestantes % 24);
	        model.addAttribute("minRestantes", minRestantes % 60);
	        model.addAttribute("nombreCompeticion", nombreCompeticion);
	        
	        
	    } else {
	        model.addAttribute("diasRestantess", "No hay competiciones próximas.");
	    }

	    return "index";
	}
	
	 
	
}
	