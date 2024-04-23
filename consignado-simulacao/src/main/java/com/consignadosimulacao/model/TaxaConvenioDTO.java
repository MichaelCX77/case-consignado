package com.consignadosimulacao.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxaConvenioDTO {

	@NotBlank(message = "taxaConvenioTipo é obrigatória")
	private String taxaConvenioTipo;
	
	@NotBlank(message = "taxaConvenioPorcentagem é obrigatória")
	private Double taxaConvenioPorcentagem;

}
