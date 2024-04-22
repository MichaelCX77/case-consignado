package com.consignadocliente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consignadocliente.repository.ConvenioRepository;

@Service
public class ConvenioService {

	
	@Autowired
	private ConvenioRepository repository;
	
}
