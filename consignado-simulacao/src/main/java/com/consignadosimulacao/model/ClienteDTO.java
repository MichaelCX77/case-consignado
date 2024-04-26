package com.consignadosimulacao.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteDTO {

	private Long id;
	
	private String cpf;
	
	private String nome;
	
	private String correntista;
	
	private String segmento;
	
	private String convenio;
	
}