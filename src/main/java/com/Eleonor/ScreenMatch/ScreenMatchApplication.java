//package com.Eleonor.ScreenMatch;
//
//import com.Eleonor.ScreenMatch.models.serie.ISerieRepository;
//import com.Eleonor.ScreenMatch.principal.Principal;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class ScreenMatchApplication implements CommandLineRunner {
//
//	@Autowired //HACE LA INYECCION DE DEPENDENCIAS
//	private ISerieRepository repository;
//
//	@Override
//	public void run(String... args) throws Exception {
//		Principal principal = new Principal(repository);
//		principal.showMenu();
//	}
//
//	public static void main(String[] args) {
//		SpringApplication.run(ScreenMatchApplication.class, args);
//
//
//	}
//
//}
