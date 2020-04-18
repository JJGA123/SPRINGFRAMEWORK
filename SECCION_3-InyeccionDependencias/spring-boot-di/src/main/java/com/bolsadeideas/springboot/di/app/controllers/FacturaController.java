package com.bolsadeideas.springboot.di.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsadeideas.springboot.di.app.models.domain.Factura;

@Controller
@RequestMapping("/factura")
public class FacturaController {

	@Autowired
	@Qualifier("mifactura")
	private Factura factura;
	
	@GetMapping("/verFactura")
	public String verFactura(Model model) {
		model.addAttribute("factura", factura);
		model.addAttribute("titulo", "Ejemplo ver factura inyectando...");
		return "factura/verFactura";
	}
}
