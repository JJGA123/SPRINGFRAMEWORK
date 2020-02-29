package com.bolsadeideas.spring.web.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsadeideas.spring.web.app.models.Usuario;

@Controller
@RequestMapping("/app")
public class IndexController {
	@Value("${texto.indexController.index.titulo}")
	private String textoIndex;
	@Value("${texto.indexController.perfil.titulo}")
	private String textoPerfil;
	@Value("${texto.indexController.listar.titulo}")
	private String textoListar;
	
	@GetMapping(value= {"/index","/","","/home"})
	public String index(Model model) {
		model.addAttribute("titulo", textoIndex);
		model.addAttribute("bienvenida", "Hola Spring Framework!");
		return "index";
	}
	
	@GetMapping(value="/perfil")
	public String perfil(Model model) {
		Usuario usuario = new Usuario();
		usuario.setApellido("Galvez Bohorquez");
		usuario.setNombre("Maria Julia Gisela");
		usuario.setEmail("sistemasjhoning@gmail.com");
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", textoPerfil.concat(usuario.getNombre()));
		return "perfil";
	}
	
	@GetMapping(value="/listar")
	public String usuarios(Model model) {
		List<Usuario> users = new ArrayList<Usuario>();
		users.add(new Usuario("Jairo Orlando","Galvis Carrillo","jairoejemplo@gmail.com"));
		users.add(new Usuario("Fanny","Arenales Ramirez","fannyejemplo@gmail.com"));
		users.add(new Usuario("Jeison Fabian","Galvis Arenales","jeisonejemplo@gmail.com"));
		users.add(new Usuario("Jhon Jairo","Galvis Arenales","jhonejemplo@gmail.com"));
		users.add(new Usuario("Angie Karina","Galvis Arenales","angieejemplo@gmail.com"));
		model.addAttribute("usuarios", users);
		model.addAttribute("titulo", textoListar);
		return "listar";
	}
	
	@ModelAttribute("usuarios")
	public List<Usuario> poblarUsuarios(Model model) {
		List<Usuario> users = new ArrayList<Usuario>();
		users.add(new Usuario("Jairo Orlando Model","Galvis Carrillo","jairoejemplo@gmail.com"));
		users.add(new Usuario("Fanny Model","Arenales Ramirez","fannyejemplo@gmail.com"));
		users.add(new Usuario("Jeison Fabian Model","Galvis Arenales","jeisonejemplo@gmail.com"));
		users.add(new Usuario("Jhon Jairo Model","Galvis Arenales","jhonejemplo@gmail.com"));
		users.add(new Usuario("Angie Karina Model","Galvis Arenales","angieejemplo@gmail.com"));
		return users;
	}
}
