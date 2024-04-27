package com.consignadocliente.v1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.consignadocliente.exception.IllegalArgumentException;
import com.consignadocliente.util.CpfUtil;
import com.consignadocliente.v1.model.Cliente;
import com.consignadocliente.v1.model.ClienteDTO;
import com.consignadocliente.v1.service.ClienteService;

@RestController
@RequestMapping(value = "/api/v1/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> getAllClientes (){
		
		List<Cliente> listUsers = service.findAll();
		return ResponseEntity.ok(convertToListClientesDTO(listUsers));
		
	}
	
	@GetMapping("/search")
	public ResponseEntity<ClienteDTO> getCliente(@RequestParam(required = false) String cpf) {

		try {
			CpfUtil.validaCpf(cpf);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		Cliente cliente = service.findClienteByCpf(cpf);

		return ResponseEntity.ok(new ClienteDTO(cliente));
	}
	

	private List<ClienteDTO> convertToListClientesDTO(List<Cliente> listClientes){
		
		List<ClienteDTO> listClientesDTO = new ArrayList<ClienteDTO>();
		
		listClientesDTO = listClientes.stream()
				.map(cliente -> new ClienteDTO(cliente))
				.collect(Collectors.toList());
		return listClientesDTO;
	}
	
}


