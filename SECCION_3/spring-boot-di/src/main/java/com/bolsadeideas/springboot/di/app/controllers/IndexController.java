package com.bolsadeideas.springboot.di.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bolsadeideas.springboot.di.app.models.services.IServicio;

@Controller
public class IndexController {

		
	@Autowired
	private IServicio servicio;
	
	@GetMapping(value = {"/","","/index"})
	public String index(Model model) {
		model.addAttribute("objeto", servicio.operacion());
		return "index";
	}

	public IServicio getServicio() {
		return servicio;
	}
	
}
