package com.consignadosimulacao.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.consignadosimulacao.model.ClienteDTO;

@Component
@FeignClient(name = "consignado-cliente", path="/clientes/search")
public interface ClienteFeignClient {

	@GetMapping
	public ResponseEntity<ClienteDTO> search(@RequestParam String cpf);
	
}