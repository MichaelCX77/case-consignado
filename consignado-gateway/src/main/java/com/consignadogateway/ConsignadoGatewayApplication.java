package com.consignadogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ConsignadoGatewayApplication {

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
		SpringApplication.run(ConsignadoGatewayApplication.class, args);
	}

}
