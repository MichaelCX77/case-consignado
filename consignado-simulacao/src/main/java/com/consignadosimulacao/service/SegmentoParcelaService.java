package com.consignadosimulacao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.consignadosimulacao.exception.ResourceNotFoundException;
import com.consignadosimulacao.model.SegmentoParcelaDTO;
import com.consignadosimulacao.repository.SegmentoParcelaRepository;

@Service
public class SegmentoParcelaService {
	
	@Autowired
	private SegmentoParcelaRepository repository;

	public SegmentoParcelaDTO findBySegmentoParcelaTipo(String segmentoParcelaTipo) {

		Optional <SegmentoParcelaDTO> segmentoPrcelaDTO = repository.findBySegmentoParcelaTipo(segmentoParcelaTipo);
		if (segmentoPrcelaDTO.isPresent()) {
			return segmentoPrcelaDTO.get();
		}
		
		throw new ResourceNotFoundException("Segmento/Prazo não encontrado: " + segmentoParcelaTipo, HttpStatus.NOT_FOUND);
		
	}
	
}
