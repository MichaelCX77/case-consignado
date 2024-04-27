package com.consignadocontrato.v1.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulacaoDTO {
	
	@NotBlank(message = "O codigo é obrigatório")
	private String codigo;
	
	@NotBlank(message = "cpf é obrigatório")
	private String cpf;
	
}
