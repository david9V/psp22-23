package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Act1PspApplication {

	public static void main(String[] args) {
		SpringApplication.run(Act1PspApplication.class, args);
	}
	
	 @Bean
	 public CommandLineRunner lombokTest() {
		 
		return args -> {
			Persona p1 = new Persona();
			p1.setDni("12345678A");
			p1.setNombre("David");
			p1.setApellidos("Vilches");
			
			System.out.println(p1);
			
			Persona p2 = new Persona("12345678B", "José", "Vilches");
			
			System.out.println(p2);
			
			if (p1.equals(p2)) {
				System.out.println("Ambas personas son iguales");
			}
			else
				System.out.println("Las dos personas no son iguales");
		};
		 
	 }

}
