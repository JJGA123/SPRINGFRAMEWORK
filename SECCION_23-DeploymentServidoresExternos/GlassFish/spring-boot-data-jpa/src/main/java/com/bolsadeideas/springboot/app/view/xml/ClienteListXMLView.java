package com.bolsadeideas.springboot.app.view.xml;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;

@Component("listar.xml")
public class ClienteListXMLView extends MarshallingView{

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	public ClienteListXMLView(Jaxb2Marshaller marshaller) {
		super(marshaller);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List<Cliente> clientes = clienteService.findAll();
		model.put("clienteList", new ClienteList(clientes));
		
		super.renderMergedOutputModel(model, request, response);
	}
	
}
