package com.bolsadeideas.spring.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsadeideas.spring.web.app.models.Usuario;

@Controller
@RequestMapping("/app")
public class IndexController {

	@GetMapping(value= {"/index","/","","/home"})
	public String index(Model model) {
		model.addAttribute("titulo", "Spring Framework 5!");
		model.addAttribute("bienvenida", "Hola Spring Framework!");
		return "index";
	}
	
	@GetMapping(value="/perfil")
	public String perfil(Model model) {
		Usuario usuario = new Usuario();
		usuario.setApellido("Galvez Bohorquez");
		usuario.setNombre("Maria Julia Gisela");
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Perfil del usuario: ".concat(usuario.getNombre()));
		return "perfil";
	}
}
