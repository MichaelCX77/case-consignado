package com.consignadocliente.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteDTO {

	private Long id;
	
	@NotBlank(message= "Cpf é obrigatório")
	private String cpf;
	
	@NotBlank(message= "Nome é obrigatório")
	private String nome;
	
	@NotBlank(message= "Correntista é obrigatório")
	private String correntista;
	
	private String segmento;
	
	@NotBlank(message= "Convenio é obrigatório")
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