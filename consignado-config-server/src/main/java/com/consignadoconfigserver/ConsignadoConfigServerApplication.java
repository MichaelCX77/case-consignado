package com.consignadoconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConsignadoConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsignadoConfigServerApplication.class, args);
	}

}
