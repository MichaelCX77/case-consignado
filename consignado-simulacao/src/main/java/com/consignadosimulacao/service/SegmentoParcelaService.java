package com.consignadosimulacao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.consignadosimulacao.exception.ResourceNotFoundException;
import com.consignadosimulacao.model.SegmentoParcela;
import com.consignadosimulacao.repository.SegmentoParcelaRepository;

@Service
public class SegmentoParcelaService {
	
	@Autowired
	private SegmentoParcelaRepository repository;

	public SegmentoParcela findBySegmentoParcelaTipo(String segmentoParcelaTipo) {

		Optional <SegmentoParcela> segmentoParcela = repository.findBySegmentoParcelaTipo(segmentoParcelaTipo);
		if (segmentoParcela.isPresent()) {
			return segmentoParcela.get();
		}
		
		throw new ResourceNotFoundException("Segmento/Prazo não encontrado: " + segmentoParcelaTipo, HttpStatus.NOT_FOUND);
		
	}
	
}
