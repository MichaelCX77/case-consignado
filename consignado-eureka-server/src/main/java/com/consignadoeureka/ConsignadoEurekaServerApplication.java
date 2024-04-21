package com.consignadoeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ConsignadoEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsignadoEurekaServerApplication.class, args);
	}

}
