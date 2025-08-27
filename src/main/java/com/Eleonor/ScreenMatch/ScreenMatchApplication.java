package com.Eleonor.ScreenMatch;

import com.Eleonor.ScreenMatch.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.buscarSerie();
	}

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);


	}

}
