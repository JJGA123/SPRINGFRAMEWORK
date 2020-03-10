package com.bolsadeideas.spring.web.app.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/params")
public class ParamsController {

	@GetMapping(value= "/")
	public String index(Model model) {
		return "params/indexParams";
	}
	
	@GetMapping(value= "string")
	public String String(@RequestParam(required=false,defaultValue = "No se encontró ningun valor.") String texto, Model model) {
		model.addAttribute("resultado", "El parametro enviado es : ".concat(texto));
		return "params/ver";
	}
	
	@GetMapping(value= "mix-params")
	public String mixParam(@RequestParam String saludo, @RequestParam Integer numero, Model model) {
		model.addAttribute("resultado", "El saludo enviado es : " + saludo + " y el número es : "+numero);
		return "params/ver";
	}
	
	@GetMapping(value= "mix-params-request")
	public String mixParam(HttpServletRequest request, Model model) {
		String saludo = request.getParameter("saludo");
		Integer numero = 0;
		try {
			numero = Integer.parseInt(request.getParameter("numero"));
		}catch (Exception e) {
			
		}
		
		model.addAttribute("resultado", "El saludo enviado es : " + saludo + " y el número es : "+numero);
		return "params/ver";
	}
}
