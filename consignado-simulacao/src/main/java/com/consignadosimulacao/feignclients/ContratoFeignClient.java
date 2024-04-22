package com.consignadosimulacao.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(name = "consignado-cliente", path="/cliente/{id}")
public interface ContratoFeignClient {

//	@GetMapping
//	public ResponseEntity<ClienteDTO> search(@RequestParam String email);
	
}