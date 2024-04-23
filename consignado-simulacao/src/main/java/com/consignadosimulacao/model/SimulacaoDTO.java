package com.consignadosimulacao.model;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulacaoDTO {
	
	private Long id;
	
	@NotBlank(message = "codigo é obrigatório")
	private String codigo;
	
	private Timestamp data;
	
	@NotBlank(message = "cpf é obrigatório")
	private String cpf;
	
	@NotBlank(message = "código é obrigatório")
	private String tipoTaxa;
	
	@NotBlank(message = "porcentagemTaxa é obrigatória")
	private Double porcentagemTaxa;
	
	@NotBlank(message = "valorSolicicado é obrigatório")
	private Float valorSolicicado;
	
	@NotBlank(message = "valorTotal é obrigatório")
	private Float valorTotal;
	
	@NotBlank(message = "valorParcela é obrigatório")
	private Float valorParcela;
	
	@NotBlank(message = "parcelaDesejada é obrigatória")
	private Integer parcelaDesejada;
	
	public SimulacaoDTO(Simulacao simulacao) {
		this.id = simulacao.getId();
		this.codigo = simulacao.getCodigo();
		this.data = simulacao.getData();
		this.cpf = simulacao.getCpf();
		this.tipoTaxa = simulacao.getTaxaTipo().getTaxaConvenioTipo();
		this.porcentagemTaxa = simulacao.getTaxaTipo().getTaxaConvenioPorcentagem();
		this.valorSolicicado = simulacao.getVlrSolicicado();
		this.valorTotal = simulacao.getVlrParcela();
		this.valorParcela = simulacao.getVlrParcela();
		this.parcelaDesejada = simulacao.getQtdParcelas();
	}
	
}
