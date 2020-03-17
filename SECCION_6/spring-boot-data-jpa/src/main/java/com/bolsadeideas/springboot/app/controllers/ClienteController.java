package com.bolsadeideas.springboot.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@RequestMapping(value= {"/listar"}, method = RequestMethod.GET)
	public String listar(@RequestParam(name="page",defaultValue="0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		PageRender<Cliente> pageRender = new PageRender<>("/listar",clientes);
		
		model.addAttribute("titulo","Listar clientes...");
		model.addAttribute("clientes",clientes);
		model.addAttribute("activeListar","active");
		model.addAttribute("page",pageRender);
		return "listar";
	}
	
	@RequestMapping(value= {"/home","","/"}, method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("titulo","Bienvenido...");
		model.addAttribute("activeHome","active");
		return "home";
	}
	
	@RequestMapping(value= {"/form"})
	public String crear(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("titulo","Formulario de cliente...");
		model.addAttribute("cliente",cliente);
		model.addAttribute("activeFormulario","active");
		return "form";
	}
	
	@RequestMapping(value= {"/form"}, method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente,BindingResult result,Model model, RedirectAttributes flash, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","Formulario crear cliente...");
			return "form";
		}
		String mensajeFlash = (cliente.getId()!=null ? "Cliente actualizado con éxito!" : "Cliente creado con éxito!");
		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/listar";
	}
	
	@RequestMapping(value= {"/form/{id}"})
	public String editar(@PathVariable(value="id") Long id,Model model, RedirectAttributes flash) {
		Cliente cliente = null; 
		model.addAttribute("titulo","Formulario editar cliente...");
		if(id>0) {
			cliente=clienteService.findOne(id);
			if(cliente==null) {
				flash.addFlashAttribute("error", "El cliente no existe!");
				return "redirect:/listar";
			}
		}else {
			flash.addFlashAttribute("error", "El ID  del cliente no puede ser cero!");
			return "redirect:/listar";
		}
		model.addAttribute("cliente",cliente);
		model.addAttribute("activeFormulario","active");
		return "form";
	}
	
	@RequestMapping(value= {"/eliminar/{id}"})
	public String eliminar(@PathVariable(value="id") Long id,Model model, RedirectAttributes flash) {
		model.addAttribute("nombreCompleto",clienteService);
		if(id>0) {
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con éxito!");
			return "redirect:/listar";
		}else {
			return "redirect:/listar";
		}
	}
}
