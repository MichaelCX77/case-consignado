package com.consignadocontrato.v1.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContratoDTO {
	
	private Long id;
	
	private String codigo;
	
	private LocalDateTime data;
	
	@NotBlank(message = "O codigoSimulacao é obrigatório")
	private String codigoSimulacao;
	
	public ContratoDTO(Contrato contrato) {
		this.id = contrato.getId();
		this.codigo = contrato.getCodigo();
		this.data = contrato.getData();
		this.codigoSimulacao = contrato.getCodigoSimulacao();
	}
}
