package com.consignadosimulacao.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.consignadosimulacao.model.Simulacao;
import com.consignadosimulacao.model.SimulacaoDTO;
import com.consignadosimulacao.service.SimulacaoService;

@RestController
@RequestMapping(value = "/simulacao")
public class SimulacaoController {
	
	@Autowired
	private SimulacaoService service;
	
	@GetMapping
	public ResponseEntity<List<SimulacaoDTO>> getAllSimulacoes (){
		
		List<Simulacao> listSimulacao = service.findAll();
		return ResponseEntity.ok(convertToListSimulacaoDTO(listSimulacao));
	}
	
	@PostMapping
	public ResponseEntity<SimulacaoDTO> createUser(@Validated @RequestBody SimulacaoDTO simulacaoDTO) {

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
