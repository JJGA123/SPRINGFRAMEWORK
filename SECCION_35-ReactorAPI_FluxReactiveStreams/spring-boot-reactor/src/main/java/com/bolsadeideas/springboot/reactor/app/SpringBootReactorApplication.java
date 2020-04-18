package com.bolsadeideas.springboot.reactor.app;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bolsadeideas.springboot.reactor.app.models.Usuario;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootReactorApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(SpringBootReactorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * Flux<String> nombre = Flux.just("Jhon Galvis", "Jairo Arenales",
		 * "Gisela Galvis", "Maria Arenales", "Bruce Lee", "Bruce Willis");
		 * Flux<Usuario> usuarios= nombre.map(nombres -> { return new
		 * Usuario(nombres.split(" ")[0].toUpperCase(),
		 * nombres.split(" ")[1].toLowerCase()); }).filter(usuario ->
		 * usuario.getNombre().equalsIgnoreCase("bruce")).doOnNext(usuario -> { if
		 * (usuario == null) { throw new
		 * RuntimeException("Nombre no pueden ser vacíos."); }
		 * System.out.println(usuario.getNombre().concat(" ").concat(usuario.getApellido
		 * ())); }).map(usuario -> { String nombres = usuario.getNombre().toLowerCase();
		 * return usuario; });
		 * 
		 * usuarios.subscribe(e -> log.info(e.toString()), error ->
		 * log.error(error.getMessage()), new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * log.info("Ha finalizado la ejecución del observable."); } });
		 */

		List<String> usuariosList = new ArrayList<String>();
		usuariosList.add("Jhon Galvis");
		usuariosList.add("Jairo Arenales");
		usuariosList.add("Gisela Galvis");
		usuariosList.add("Maria Arenales");
		usuariosList.add("Bruce Lee");
		usuariosList.add("Bruce Willis");
		
		Flux<String> nombre = Flux.fromIterable(usuariosList);
		Flux<Usuario> usuarios = nombre.map(nombres -> {
			return new Usuario(nombres.split(" ")[0].toUpperCase(), nombres.split(" ")[1].toLowerCase());
		}).filter(usuario -> usuario.getNombre().equalsIgnoreCase("bruce")).doOnNext(usuario -> {
			if (usuario == null) {
				throw new RuntimeException("Nombre no pueden ser vacíos.");
			}
			System.out.println(usuario.getNombre().concat(" ").concat(usuario.getApellido()));
		}).map(usuario -> {
			String nombres = usuario.getNombre().toLowerCase();
			return usuario;
		});

		usuarios.subscribe(e -> log.info(e.toString()), error -> log.error(error.getMessage()), new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				log.info("Ha finalizado la ejecución del observable.");
			}
		});

	}

}
