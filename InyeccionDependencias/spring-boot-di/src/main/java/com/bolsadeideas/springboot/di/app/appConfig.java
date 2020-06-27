package com.bolsadeideas.springboot.di.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.context.annotation.RequestScope;

import com.bolsadeideas.springboot.di.app.models.domain.Cliente;
import com.bolsadeideas.springboot.di.app.models.domain.Factura;
import com.bolsadeideas.springboot.di.app.models.domain.ItemFactura;
import com.bolsadeideas.springboot.di.app.models.domain.Producto;
import com.bolsadeideas.springboot.di.app.models.services.IServicio;
import com.bolsadeideas.springboot.di.app.models.services.MiServicio;
import com.bolsadeideas.springboot.di.app.models.services.MiServicioComplejo;

@Configuration
public class appConfig {

	@Bean("miServicioSimple")
	@Primary
	public IServicio registrarMiServicio() {
		return new MiServicio();
	}
	
	@Bean("miServicioComplejo")
	public IServicio registrarMiComplejo() {
		return new MiServicioComplejo();
	}
	
	@Bean("micliente")
	@RequestScope
	public Cliente registrarCliente() {
		return new Cliente();
	}
		
	@Bean("mifactura")
	@RequestScope
	public Factura registrarFactura() {
		return new Factura();
	}
	
	@Bean("itemsFactura")
	public List<ItemFactura> registrarItems() {
		Producto p1 = new Producto("Camara Sony", 350000);
		Producto p2 = new Producto("Biciclea Calloy", 500000);
		
		ItemFactura linea1 = new ItemFactura(p1, 3);
		ItemFactura linea2 = new ItemFactura(p2, 6);
		
		return Arrays.asList(linea1,linea2);
	}
	
	@Bean("itemsFacturaOficina")
	public List<ItemFactura> registrarItemsOficina() {
		Producto p1 = new Producto("Monitor LG", 450000);
		Producto p2 = new Producto("NoteBook Asus", 5000000);
		Producto p3 = new Producto("Impresora HP", 60000);
		Producto p4 = new Producto("Escritorio Oficina", 30000);
		
		ItemFactura linea1 = new ItemFactura(p1, 3);
		ItemFactura linea2 = new ItemFactura(p2, 6);
		ItemFactura linea3 = new ItemFactura(p3, 2);
		ItemFactura linea4 = new ItemFactura(p4, 4);
		
		return Arrays.asList(linea1,linea2,linea3,linea4);
	}
}
