package com.consignadosimulacao.v1.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.consignadosimulacao.exception.BusinessException;
import com.consignadosimulacao.exception.FeignClientException;
import com.consignadosimulacao.exception.ResourceNotFoundException;
import com.consignadosimulacao.util.DateUtil;
import com.consignadosimulacao.util.ErrorResponse;
import com.consignadosimulacao.v1.feignclients.ClienteFeignClient;
import com.consignadosimulacao.v1.model.BaseCalculo;
import com.consignadosimulacao.v1.model.ClienteDTO;
import com.consignadosimulacao.v1.model.SegmentoParcela;
import com.consignadosimulacao.v1.model.Simulacao;
import com.consignadosimulacao.v1.model.SimulacaoDTO;
import com.consignadosimulacao.v1.model.TaxaConvenio;
import com.consignadosimulacao.v1.repository.SimulacaoRepository;

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
	
    public Simulacao findByCodigo(String codigo) {
    	
    	Optional<Simulacao> simulacaoOpt = repository.findByCodigo(codigo);
    	
        if (simulacaoOpt.isPresent()) {
        	return simulacaoOpt.get();
        } 
           
        throw new ResourceNotFoundException("Simulacao não encontrada: " + codigo, HttpStatus.NOT_FOUND);
    }

	public Simulacao save(SimulacaoDTO simulacaoDTO) {
		
		ClienteDTO clienteDTO  = null;
		
		try {
			clienteDTO =  clienteFeignClient.search(simulacaoDTO.getCpf()).getBody();
		} catch (FeignClientException e) {
			ErrorResponse error = e.getErrorResponse();
			throw new ResourceNotFoundException(error.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		Double taxa = getTaxa(clienteDTO.getConvenio());
		boolean isCorrentista = (clienteDTO.getCorrentista().equals("S")) ? true  : false;
		
		validaParcelaMaxima(simulacaoDTO, clienteDTO.getSegmento(),isCorrentista);

		BaseCalculo base = new BaseCalculo(isCorrentista, taxa, simulacaoDTO.getQtdParcelas(), simulacaoDTO.getValorSolicitado());
		
		Simulacao simulacao = calculaConsignado(base);
        simulacao.setData(DateUtil.getFormatedActualDate());
		simulacao.setCodigo("SM-"+ DateUtil.getUnformattedTimestamp());
		simulacao.setCpf(simulacaoDTO.getCpf());
		simulacao.setVlrSolicicado(simulacaoDTO.getValorSolicitado());
		simulacao.setQtdParcelas(simulacaoDTO.getQtdParcelas());
		
		TaxaConvenio taxaConvenio = new TaxaConvenio();
		taxaConvenio.setTaxaConvenioTipo(clienteDTO.getConvenio());
		simulacao.setTaxaTipo(taxaConvenio);
		
		return repository.save(simulacao);
		
	}
	
	public Double getTaxa(String taxaTipo) {
		
		return taxaConvenioSevice.findByTaxaConvenioTipo(taxaTipo).getTaxaConvenioPorcentagem();
		
	}
	
	private void validaParcelaMaxima(SimulacaoDTO simulacaoDTO, String segmento, boolean isCorrentista) {
		
		
		SegmentoParcela parcela = null;
		
		if (isCorrentista) {
			parcela = segmentoParcelaService.findBySegmentoParcelaTipo(segmento);
			
		} else {
			parcela = segmentoParcelaService.findBySegmentoParcelaTipo("NOTHING");
		}
		
		Integer parcelaMaxima = parcela.getParcelaQtd();
		
		if (simulacaoDTO.getQtdParcelas() > parcelaMaxima) {
			if (isCorrentista)	throw new BusinessException("prazoDesejado inválido, o prazo máximo para o segmento "+ segmento + " é : " + parcelaMaxima, HttpStatus.BAD_REQUEST);
			throw new BusinessException("prazoDesejado inválido, o prazo máximo para pessoas não correntistas é : " + parcelaMaxima, HttpStatus.BAD_REQUEST);
		} 
		
		
	}
	
	private Simulacao calculaConsignado(BaseCalculo baseCalculo) {
		
		Double vlrFinalTaxa = null;
		Double porcentagemTaxa = null;
		
		
		if(baseCalculo.isCorrentista()) {
			porcentagemTaxa = baseCalculo.getTaxa() * 0.95;
		} else {
			porcentagemTaxa = baseCalculo.getTaxa();
		}
		
		vlrFinalTaxa = (((baseCalculo.getVlrSolicitado() / 100) * porcentagemTaxa));
		
		
		Locale.setDefault(Locale.US);
		Double vlrTotal = (baseCalculo.getVlrSolicitado() + (vlrFinalTaxa * baseCalculo.getQtdParcelasDesejada()));
		Double vlrParcela = vlrTotal/baseCalculo.getQtdParcelasDesejada();


		
		DecimalFormat df = new DecimalFormat("#0.00");
		
		Simulacao resultadoSimulacao = new Simulacao();
		resultadoSimulacao.setVlrTotal(Double.parseDouble(df.format(vlrTotal)));
		resultadoSimulacao.setVlrParcela(Double.parseDouble(df.format(vlrParcela)));
		resultadoSimulacao.setTaxaPorcentagem(df.format(porcentagemTaxa)+"%");
		
		return resultadoSimulacao;
	}
	
}
