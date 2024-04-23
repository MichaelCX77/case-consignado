package com.consignadosimulacao.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SegmentoParcelaDTO {

	@NotBlank(message = "segmentoParcelaTipo é obrigatório")
	private String segmentoParcelaTipo;
	
	@NotBlank(message = "segmentoParcelaQtd é obrigatório")
	private Integer segmentoParcelaQtd;

}
