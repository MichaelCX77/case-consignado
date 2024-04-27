package com.consignadosimulacao.v1.controller;

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

import com.consignadosimulacao.v1.model.Simulacao;
import com.consignadosimulacao.v1.model.SimulacaoDTO;
import com.consignadosimulacao.v1.service.SimulacaoService;

@RestController
@RequestMapping(value = "/api/v1/simulacoes")
public class SimulacaoController {
	
	@Autowired
	private SimulacaoService service;
	
	@GetMapping
	public ResponseEntity<List<SimulacaoDTO>> getAllSimulacoes (){
		
		List<Simulacao> listSimulacao = service.findAll();
		return ResponseEntity.ok(convertToListSimulacaoDTO(listSimulacao));
	}
	
	@GetMapping("/search")
	public ResponseEntity<SimulacaoDTO> getSimulacaoByCodigo (@RequestParam(required = false) String codigo){
		
		Simulacao simulacao = service.findByCodigo(codigo);
		return ResponseEntity.ok(new SimulacaoDTO(simulacao));
	}
	
	@PostMapping
	public ResponseEntity<SimulacaoDTO> createSimulacao(@Validated @RequestBody SimulacaoDTO simulacaoDTO) {

		Simulacao simulacao = service.save(simulacaoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(new SimulacaoDTO(simulacao));
					
	}
	
	private List<SimulacaoDTO> convertToListSimulacaoDTO(List<Simulacao> listSimulacao){
		
		List<SimulacaoDTO> listSimulacaoDTO = new ArrayList<SimulacaoDTO>();
		
		listSimulacaoDTO = listSimulacao.stream()
				.map(simulacao -> new SimulacaoDTO(simulacao))
				.collect(Collectors.toList());
		return listSimulacaoDTO;
	}
}
