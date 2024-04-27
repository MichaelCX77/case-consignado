package com.consignadocontrato.v1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.consignadocontrato.v1.model.Contrato;
import com.consignadocontrato.v1.model.ContratoDTO;
import com.consignadocontrato.v1.model.SimulacaoDTO;
import com.consignadocontrato.v1.service.ContratoService;

@RestController
@RequestMapping(value = "/api/v1/contratos")
public class ContratoController {
	
	@Autowired
	private ContratoService service;
	
	
	@GetMapping
	public ResponseEntity<List<ContratoDTO>> getAllContratos (){
		
		List<Contrato> listContratos = service.findAll();
		return ResponseEntity.ok(convertToListContratosDTO(listContratos));
		
	}
	
	@PostMapping
	public ResponseEntity<ContratoDTO> createContrato(@Validated @RequestBody SimulacaoDTO simulacaoDTO) {

		Contrato contrato = service.save(simulacaoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ContratoDTO(contrato));
					
	}
	
	private List<ContratoDTO> convertToListContratosDTO(List<Contrato> listContratos){
		
		List<ContratoDTO> listContratosDTO = new ArrayList<ContratoDTO>();
		
		listContratosDTO = listContratos.stream()
				.map(contrato -> new ContratoDTO(contrato))
				.collect(Collectors.toList());
		return listContratosDTO;
	}
	
	@GetMapping("/search")
	public ResponseEntity<ContratoDTO> getContratoByCodigo (@RequestParam(required = false) String codigo){
		
		Contrato simulacao = service.findByCodigo(codigo);
		return ResponseEntity.ok(new ContratoDTO(simulacao));
	}

		
}
