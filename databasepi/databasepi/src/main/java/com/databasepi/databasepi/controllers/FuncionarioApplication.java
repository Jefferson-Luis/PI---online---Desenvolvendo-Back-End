package com.databasepi.databasepi.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EntityScan(basePackages = "com.funcionario.model")
//@EnableJpaRepositories("com.funcionario.repository")


@SpringBootApplication
@ComponentScan(basePackages = {"com.databasepi.databasepi.FuncionarioApplication"})

	public class FuncionarioApplication {	



	/*
	 * Iniciando Spring Boot em uma aplicação Web
	 * 
	 * Nesta classe - Run As > Java Aplication
	 * 
	 * Depois de carregar tudo ir no http://localhost:8080/ carregou o projeto
	 * 
	 */

	public static void main(String[] args) {
		SpringApplication.run(FuncionarioApplication.class, args);
	}

}
