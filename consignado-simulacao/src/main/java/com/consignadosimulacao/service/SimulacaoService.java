package com.consignadosimulacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.consignadosimulacao.exception.BusinessException;
import com.consignadosimulacao.feignclients.ClienteFeignClient;
import com.consignadosimulacao.model.ClienteDTO;
import com.consignadosimulacao.model.SegmentoParcelaDTO;
import com.consignadosimulacao.model.Simulacao;
import com.consignadosimulacao.model.SimulacaoDTO;
import com.consignadosimulacao.repository.SimulacaoRepository;

@Service
public class SimulacaoService {

	@Autowired
	private SimulacaoRepository repository;
	
	@Autowired
	private TaxaConvenioService taxaConvenioSevice;
	
	@Autowired
	private SegmentoParcelaService segmentoParcelaService;
	
	@Autowired
	private ClienteFeignClient clienteFeignClient;
	
	public List<Simulacao> findAll() {
		return repository.findAll();
	}

	public Simulacao save(SimulacaoDTO simulacaoDTO) {
		
		ClienteDTO clienteDTO =  clienteFeignClient.search(simulacaoDTO.getCpf()).getBody();
		Double taxa = getTaxa(clienteDTO.getConvenio());
		
		
		boolean isCorrentista = (clienteDTO.getCorrentista() == "S") ? true  : false;
		
		Integer qtdMeses = 12;
		validaParcelaMaxima(simulacaoDTO, clienteDTO.getSegmento(),isCorrentista);

//		Implementa calculo do consignado
//		Implementa calculo do consignado
//		Implementa calculo do consignado	
//		calculaConsignado();
		
		return null;

		
		
	}
	
	public Double getTaxa(String taxaTipo) {
		
		return taxaConvenioSevice.findByTaxaConvenioTipo(taxaTipo).getTaxaConvenioPorcentagem();
		
	}
	
	private void validaParcelaMaxima(SimulacaoDTO simulacaoDTO, String segmento, boolean isCorrentista) {
		
		
		SegmentoParcelaDTO parcela = null;
		
		if (isCorrentista) {
			parcela = segmentoParcelaService.findBySegmentoParcelaTipo(segmento);
			
		} else {
			parcela = segmentoParcelaService.findBySegmentoParcelaTipo("NOTHING");
		}
		
		Integer parcelaMaxima = parcela.getSegmentoParcelaQtd();
		
		if (simulacaoDTO.getParcelaDesejada() > parcelaMaxima) {
			if (isCorrentista) {
				throw new BusinessException("prazoDesejado inválido, o prazo máximo para o segmento "+ segmento + " é : " + parcelaMaxima, HttpStatus.BAD_REQUEST);
			}
			throw new BusinessException("prazoDesejado inválido, o prazo máximo para pessoas não correntistas é : " + parcelaMaxima, HttpStatus.BAD_REQUEST);
		} 
	}
	
	private void calculaConsignado(SimulacaoDTO simulacaoDTO) {
		
//		Implementa calculo do consignado
//		Implementa calculo do consignado
//		Implementa calculo do consignado	

	}
}
