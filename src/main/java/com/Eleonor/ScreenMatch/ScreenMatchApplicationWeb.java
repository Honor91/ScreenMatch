package com.Eleonor.ScreenMatch;

import com.Eleonor.ScreenMatch.models.serie.ISerieRepository;
import com.Eleonor.ScreenMatch.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatchApplicationWeb {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplicationWeb.class, args);


	}

}
