package com.bolsadeideas.springboot.app.view.json;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.view.xml.ClienteList;

@Component("listar.json")
public class ClienteListJsonView extends MappingJackson2JsonView{
	
	@Autowired
	private IClienteService clienteService;
	
	@Override
	protected Object filterModel(Map<String, Object> model) {
		// TODO Auto-generated method stub
		/*model.remove("titulo");
		model.remove("page");
		
		@SuppressWarnings("unchecked")
		Page<Cliente> clientes = (Page<Cliente>) model.get("clientes");
		model.remove("cliente");
		model.remove("clientes");
		model.put("clientesJson", clientes.getContent());*/
		model.remove("activeListar");
		model.remove("cliente");
		model.remove("titulo");
		model.remove("page");
		model.remove("clientes");
		
		List<Cliente> clientes = clienteService.findAll();
		model.put("clienteList", new ClienteList(clientes));
		
		return super.filterModel(model);
	}
	
}
