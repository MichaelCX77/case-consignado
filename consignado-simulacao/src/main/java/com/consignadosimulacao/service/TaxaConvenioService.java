package com.consignadosimulacao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.consignadosimulacao.exception.ResourceNotFoundException;
import com.consignadosimulacao.model.TaxaConvenio;
import com.consignadosimulacao.model.TaxaConvenioDTO;
import com.consignadosimulacao.repository.TaxaConvenioRepository;

@Service
public class TaxaConvenioService {
	
	@Autowired
	private TaxaConvenioRepository repository;

	public TaxaConvenio findByTaxaConvenioTipo(String taxaConvenioTipo) {

		Optional <TaxaConvenio> taxaConvenio = repository.findByTaxaConvenioTipo(taxaConvenioTipo);
		if (taxaConvenio.isPresent()) {
			return taxaConvenio.get();
		}
		
		throw new ResourceNotFoundException("Taxa não encontrada: " + taxaConvenioTipo, HttpStatus.NOT_FOUND);
		
	}
	
}
