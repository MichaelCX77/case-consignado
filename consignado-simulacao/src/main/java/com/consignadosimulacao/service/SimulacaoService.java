package com.consignadosimulacao.service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.consignadosimulacao.exception.BusinessException;
import com.consignadosimulacao.exception.ResourceNotFoundException;
import com.consignadosimulacao.feignclients.ClienteFeignClient;
import com.consignadosimulacao.model.BaseCalculo;
import com.consignadosimulacao.model.ClienteDTO;
import com.consignadosimulacao.model.SegmentoParcela;
import com.consignadosimulacao.model.Simulacao;
import com.consignadosimulacao.model.SimulacaoDTO;
import com.consignadosimulacao.model.TaxaConvenio;
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
	
    public Simulacao findByCodigo(String codigo) {
    	
    	Optional<Simulacao> simulacaoOpt = repository.findByCodigo(codigo);
    	
        if (simulacaoOpt.isPresent()) {
        	return simulacaoOpt.get();
        } 
           
        throw new ResourceNotFoundException("Simulacao não encontrada: " + codigo, HttpStatus.NOT_FOUND);
    }

	public Simulacao save(SimulacaoDTO simulacaoDTO) {
		
		ClienteDTO clienteDTO =  clienteFeignClient.search(simulacaoDTO.getCpf()).getBody();
		Double taxa = getTaxa(clienteDTO.getConvenio());
		boolean isCorrentista = (clienteDTO.getCorrentista().equals("S")) ? true  : false;
		validaParcelaMaxima(simulacaoDTO, clienteDTO.getSegmento(),isCorrentista);

		BaseCalculo base = new BaseCalculo(isCorrentista, taxa, simulacaoDTO.getQtdParcelas(), simulacaoDTO.getValorSolicitado());
		Simulacao simulacao = calculaConsignado(base);
		
        LocalDateTime now = LocalDateTime.now();
        simulacao.setData(now);
        
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmssSSS");
        DecimalFormat df = new DecimalFormat("#.00");
        Timestamp timestamp = Timestamp.valueOf(now);
        String formattedDate = sdf.format(timestamp.getTime());
        
		simulacao.setCodigo("SM-"+ formattedDate);
		
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
		
		Double vlrTotal = (baseCalculo.getVlrSolicitado() + (vlrFinalTaxa * baseCalculo.getQtdParcelasDesejada()));
		Double vlrParcela = vlrTotal/baseCalculo.getQtdParcelasDesejada();

		Simulacao resultadoSimulacao = new Simulacao();
		resultadoSimulacao.setVlrTotal(vlrTotal);
		resultadoSimulacao.setVlrParcela(vlrParcela);
		
		DecimalFormat df = new DecimalFormat("#.00");
		resultadoSimulacao.setTaxaPorcentagem(df.format(porcentagemTaxa)+"%");
		
		return resultadoSimulacao;
	}
	
	
//	private String geraTxtCalculoTaxa(String taxa, boolean isCorrentista, String vlrFinalTaxa) {
//		
//		String txtCalculoTaxa = taxa+"%";
//		return txtCalculoTaxa += isCorrentista ? (" x 95% = "+ vlrFinalTaxa) : "";
//	}
	
	
//	public static void main(String[] args) throws ParseException {
//		
////		BaseCalculo baseCalculo = new BaseCalculo();
////		baseCalculo.setCorrentista(false);
////		baseCalculo.setVlrSolicitado(500.00f);
////		baseCalculo.setTaxa(1.6);
////		baseCalculo.setQtdParcelasDesejada(12);
//
//		BaseCalculo baseCalculo = new BaseCalculo();
//		baseCalculo.setCorrentista(true);
//		baseCalculo.setVlrSolicitado(1000.00f);
//		baseCalculo.setTaxa(2.6);
//		baseCalculo.setQtdParcelasDesejada(10);
//		
//		calculaConsignado(baseCalculo);
//	}
	
}
