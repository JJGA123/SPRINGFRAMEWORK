package com.bolsadeideas.springboot.webflux.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.bolsadeideas.springboot.webflux.app.models.dao.ProductoDao;
import com.bolsadeideas.springboot.webflux.app.models.documents.Producto;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootWebfluxApplication implements CommandLineRunner{

	@Autowired
	private ProductoDao productoDao;
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(SpringBootWebfluxApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		mongoTemplate.dropCollection("productos").subscribe();
		Flux.just(new Producto("Tv", 30000.0),
				new Producto("Nevera", 31000.0),
				new Producto("Plancha", 32000.0),
				new Producto("Cama", 33000.0),
				new Producto("Silla", 34000.0),
				new Producto("Mesa", 35000.0)
				)
		.flatMap(producto -> productoDao.save(producto))
		.subscribe(producto -> log.info("Insert : "+producto.getId()+" "+producto.getNombre()));
	}

}
