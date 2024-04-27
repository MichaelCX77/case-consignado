package com.consignadocliente.v1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.consignadocliente.exception.ResourceNotFoundException;
import com.consignadocliente.v1.model.Cliente;
import com.consignadocliente.v1.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente findClienteByCpf(String cpf) {

		Optional <Cliente> userOpt = repository.findByCpf(cpf);
		if (userOpt.isPresent()) {
			return userOpt.get();
		}
		
		throw new ResourceNotFoundException("CPF não encontrado: " + cpf, HttpStatus.NOT_FOUND);
		
	}
	
}
