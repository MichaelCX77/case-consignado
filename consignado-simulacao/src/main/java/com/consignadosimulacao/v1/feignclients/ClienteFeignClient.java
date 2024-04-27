package com.consignadosimulacao.v1.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.consignadosimulacao.v1.model.ClienteDTO;

@Component
@FeignClient(name = "consignado-cliente", path="/api/v1/clientes/search")
public interface ClienteFeignClient {

	@GetMapping
	public ResponseEntity<ClienteDTO> search(@RequestParam String cpf);
	
}