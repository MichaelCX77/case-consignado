package com.consignadocliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConsignadoClienteApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ConsignadoClienteApplication.class, args);
	}

}
