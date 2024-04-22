package com.consignadocliente.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteDTO {

	private Long id;
	
	@NotNull
	private String cpf;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String correntista;
	
	private String segmento;
	
	@NotNull
	private String convenio;
	
	public ClienteDTO (Cliente cliente) {
		this.id = cliente.getId();
		this.cpf = cliente.getCpf();
		this.nome = cliente.getNome();
		this.correntista = cliente.getIsCorrentista().toString();
		this.segmento = (cliente.getSegmento() == null  ? null : cliente.getSegmento().getSegmentoTipo());
		this.convenio = cliente.getConvenio().getConvenioTipo();
	}

}