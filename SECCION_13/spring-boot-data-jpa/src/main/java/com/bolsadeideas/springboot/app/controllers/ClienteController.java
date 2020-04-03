package com.bolsadeideas.springboot.app.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private MessageSource messageSource;
	private final static String UPLOAD_FILE="uploads";
	
	@Secured("ROLE_USER")
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value="id") Long id,Model model,RedirectAttributes flash) {
		Cliente cliente = clienteService.fetchByIdWithFacturas(id);
		if(cliente == null) {
			flash.addFlashAttribute("error","El cliente no existe en la base de datos.");
			return "redirect:/listar";
		}
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Detalle cliente: "+ cliente.getNombre() +" "+cliente.getApellido());
		return "ver";
	}
	
	@RequestMapping(value= {"/listar"}, method = RequestMethod.GET)
	public String listar(@RequestParam(name="page",defaultValue="0") int page, Model model, Authentication authentication, HttpServletRequest request, Locale locale) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null) {
			System.out.println("Usuario autenticado.");
		}
		
		if(hasRole("ROLE_ADMIN")) {
			System.out.println("Tienes acceso.");
		}
		
		SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request,"");
		if(securityContext.isUserInRole("ROLE_ADMIN")) {
			System.out.println("Tienes acceso.");
		}
		
		if(request.isUserInRole("ROLE_ADMIN")) {
			System.out.println("Tienes acceso.");
		}
		
		
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		PageRender<Cliente> pageRender = new PageRender<>("/listar",clientes);
		
		model.addAttribute("titulo",messageSource.getMessage("text.cliente.listar.titulo",null, locale));
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
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value= {"/form"})
	public String crear(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("titulo","Formulario de cliente...");
		model.addAttribute("cliente",cliente);
		model.addAttribute("activeFormulario","active");
		return "form";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value= {"/form"}, method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente,BindingResult result,Model model,@RequestParam("file") MultipartFile foto,RedirectAttributes flash, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","Formulario crear cliente...");
			return "form";
		}
		
		if(!foto.isEmpty()) {
			if(cliente.getFoto()!=null && cliente.getFoto().length()>0) {
				Path rootPath = Paths.get(UPLOAD_FILE).resolve(cliente.getFoto()).toAbsolutePath();
				File archivo = rootPath.toFile();
				if(archivo.exists() && archivo.canRead()) {
					if(archivo.delete()) {
						flash.addFlashAttribute("info", "Foto "+cliente.getFoto()+" eliminada con exito!");
					}
				}
			}
			String uniqueFileName = UUID.randomUUID().toString()+ "_" + foto.getOriginalFilename();
			Path rootPath = Paths.get(UPLOAD_FILE).resolve(foto.getOriginalFilename());
			Path rootPathAbsolut= rootPath.toAbsolutePath();
			try {
				Files.copy(foto.getInputStream(), rootPathAbsolut);
				flash.addFlashAttribute("info","Has subido correctamente '"+uniqueFileName+"'");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String mensajeFlash = (cliente.getId()!=null ? "Cliente "+ cliente.getNombre()+" "+cliente.getApellido() +" actualizado con éxito!" : "Cliente "+ cliente.getNombre()+" "+cliente.getApellido() +" creado con éxito!");
		cliente.setFoto(foto.getOriginalFilename());
		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/listar";
	}
	
	@Secured("ROLE_ADMIN")
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
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value= {"/eliminar/{id}"})
	public String eliminar(@PathVariable(value="id") Long id,Model model, RedirectAttributes flash) {
		model.addAttribute("nombreCompleto",clienteService);
		if(id>0) {
			Cliente cliente = clienteService.findOne(id);
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con éxito!");
			Path rootPath = Paths.get(UPLOAD_FILE).resolve(cliente.getFoto()).toAbsolutePath();
			File archivo = rootPath.toFile();
			if(archivo.exists() && archivo.canRead()) {
				if(archivo.delete()) {
					flash.addFlashAttribute("info", "Foto "+cliente.getFoto()+" eliminada con exito!");
				}
			}
			return "redirect:/listar";
		}else {
			return "redirect:/listar";
		}
	}
	
	private boolean hasRole(String role) {
		SecurityContext context = SecurityContextHolder.getContext();
		if(context == null) {
			return false;
		}
		
		Authentication auth = context.getAuthentication();
		if(auth == null) {
			return false;
		}
		
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		
		for(GrantedAuthority authority: authorities) {
			if(role.equals(authority.getAuthority())) {
				return true;
			}
		}
		//return authorities.contains(new SimpleGrantedAuthority(role));
		return false;
	}
}
