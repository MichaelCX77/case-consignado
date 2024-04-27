package com.consignadocontrato.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.consignadocontrato.model.SimulacaoDTO;

@Component
@FeignClient(name = "consignado-simulacao", path="/simulacoes/search")
public interface SimulacaoFeignClient {

	@GetMapping
	public ResponseEntity<SimulacaoDTO> search(@RequestParam String codigo);
	
}