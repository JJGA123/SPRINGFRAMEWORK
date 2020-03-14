package com.bolsadeideas.springboot.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@RequestMapping(value= {"/listar"}, method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo","Listar clientes...");
		model.addAttribute("clientes",clienteService.findAll());
		return "listar";
	}
	
	@RequestMapping(value= {"/home","","/"}, method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("titulo","Bienvenido...");
		return "home";
	}
	
	@RequestMapping(value= {"/form"})
	public String crear(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("titulo","Formulario de cliente...");
		model.addAttribute("cliente",cliente);
		return "form";
	}
	
	@RequestMapping(value= {"/form"}, method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente,BindingResult result,Model model, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","Formulario crear cliente...");
			return "form";
		}
		clienteService.save(cliente);
		status.setComplete();
		return "redirect:listar";
	}
	
	@RequestMapping(value= {"/form/{id}"})
	public String editar(@PathVariable(value="id") Long id,Model model) {
		Cliente cliente = null; 
		if(id>0) {
			cliente=clienteService.findOne(id);
		}else {
			return "redirect:listar";
		}
		model.addAttribute("titulo","Formulario editar cliente...");
		model.addAttribute("cliente",cliente);
		return "form";
	}
	
	@RequestMapping(value= {"/eliminar/{id}"})
	public String eliminar(@PathVariable(value="id") Long id,Model model) {
		model.addAttribute("nombreCompleto",clienteService);
		if(id>0) {
			clienteService.delete(id);
		}else {
			return "redirect:/listar";
		}
		return "redirect:/listar";
	}
}
