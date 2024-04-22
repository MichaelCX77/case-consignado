package com.consignadocontrato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConsignadoContratoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ConsignadoContratoApplication.class, args);
	}

}
