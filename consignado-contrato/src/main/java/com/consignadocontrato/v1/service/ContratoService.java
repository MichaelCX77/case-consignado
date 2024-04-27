package com.consignadocontrato.v1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.consignadocontrato.exception.DataInconsistencyException;
import com.consignadocontrato.exception.DataIntegrityViolationException;
import com.consignadocontrato.exception.FeignClientException;
import com.consignadocontrato.exception.ResourceNotFoundException;
import com.consignadocontrato.util.DateUtil;
import com.consignadocontrato.util.ErrorResponse;
import com.consignadocontrato.v1.feignclients.SimulacaoFeignClient;
import com.consignadocontrato.v1.model.Contrato;
import com.consignadocontrato.v1.model.SimulacaoDTO;
import com.consignadocontrato.v1.repository.ContratoRepository;

@Service
public class ContratoService {

	@Autowired
	private ContratoRepository repository;

	@Autowired
	private SimulacaoFeignClient simulacaoFeignClient;

	public List<Contrato> findAll() {
		return repository.findAll();
	}

    public Contrato findByCodigo(String codigo) {
    	
    	Optional<Contrato> simulacaoOpt = repository.findByCodigo(codigo);
    	
        if (simulacaoOpt.isPresent()) {
        	return simulacaoOpt.get();
        } 
           
        throw new ResourceNotFoundException("Simulacao não encontrada: " + codigo, HttpStatus.NOT_FOUND);
    }
    
	public Contrato findByCodigoSimulacao(String codigoSimulacao) {
		
    	Optional<Contrato> simulacaoOpt = repository.findByCodigoSimulacao(codigoSimulacao);
    	
        if (simulacaoOpt.isPresent()) {
        	return simulacaoOpt.get();
        }
           
        throw new ResourceNotFoundException("Contrato não encontrado com o código de simulação: " + codigoSimulacao, HttpStatus.NOT_FOUND);

	}
	
	public Contrato save(SimulacaoDTO simulacaoDTO) {

		SimulacaoDTO simulacaoDTOFeign = null;
		
		try {
			simulacaoDTOFeign =  simulacaoFeignClient.search(simulacaoDTO.getCodigo()).getBody();
		} catch (FeignClientException e) {
			ErrorResponse error = e.getErrorResponse();
			throw new ResourceNotFoundException(error.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {
			findByCodigoSimulacao(simulacaoDTOFeign.getCodigo());
			throw new DataIntegrityViolationException("Não é possível abrir um contrato com essa simulação pois ela já foi utilizada em outro contrato, crie uma nova simulação", HttpStatus.BAD_REQUEST);
		} catch (ResourceNotFoundException e) {
			System.out.println("Simulação válida " + simulacaoDTOFeign.getCodigo());
		}
		
		if(!simulacaoDTOFeign.getCpf().equals(simulacaoDTO.getCpf())) {
			throw new DataInconsistencyException("A Simulação não possui o cpf informado", HttpStatus.BAD_REQUEST);
		}
        
		Contrato contrato = new Contrato();
		contrato.setCodigo("CT-"+DateUtil.getUnformattedTimestamp());
		contrato.setData(DateUtil.getFormatedActualDate());
		contrato.setCodigoSimulacao(simulacaoDTO.getCodigo());

		return repository.save(contrato);
		
	}

}
