package com.bolsadeideas.spring.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/variables")
public class variablesRutaController {

	@GetMapping(value= "/")
	public String index(Model model) {
		model.addAttribute("titulo","Enviar parámetros de la ruta (@PathVariable)");
		return "variables/indexVariables";
	}
	
	@GetMapping(value= "/string/{texto}")
	public String variables(@PathVariable String texto, Model model) {
		model.addAttribute("titulo","Recibir parámetros de la ruta (@PathVariable)");
		model.addAttribute("resultado","El texto enviado a la ruta (@PathVariable) es : ".concat(texto));
		return "variables/verVariables";
	}
	
	@GetMapping(value= "/string/{texto}/{numero}")
	public String variablesMixto(@PathVariable String texto, @PathVariable Integer numero,Model model) {
		model.addAttribute("titulo","Recibir parámetros de la ruta (@PathVariable)");
		model.addAttribute("resultado","El texto enviado a la ruta (@PathVariable) es : "+ texto + " y el número es : "+ numero);
		return "variables/verVariables";
	}
}
