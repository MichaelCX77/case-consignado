package com.consignadocliente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consignadocliente.repository.SegmentoRepository;

@Service
public class SegmentoService {

	
	@Autowired
	private SegmentoRepository repository;
	
}
