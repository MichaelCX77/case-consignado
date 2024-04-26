package com.consignadosimulacao.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulacaoDTO {
	
	private Long id;
	
	private String codigo;
	
	private LocalDateTime data;
	
	@NotBlank(message = "cpf é obrigatório")
	private String cpf;
	
	private String tipoTaxa;
	
	private String porcentagemTaxa;
	
	@NotNull(message = "valorSolicicado é obrigatório")
	private Double valorSolicitado;
	
	private Double valorTotal;
	
	private Double valorParcela;
	
	@NotNull(message = "qtdParcelas é obrigatório")
	private Integer qtdParcelas;
	
	public SimulacaoDTO(Simulacao simulacao) {
		this.id = simulacao.getId();
		this.codigo = simulacao.getCodigo();
		this.data = simulacao.getData();
		this.cpf = simulacao.getCpf();
		this.tipoTaxa = simulacao.getTaxaTipo().getTaxaConvenioTipo();
		this.porcentagemTaxa = simulacao.getTaxaPorcentagem();
		this.valorSolicitado = simulacao.getVlrSolicicado();
		this.valorTotal = simulacao.getVlrTotal();
		this.valorParcela = simulacao.getVlrParcela();
		this.qtdParcelas = simulacao.getQtdParcelas();
	}
	
}
