package com.consignadocliente.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Convenio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "convenio_tipo")
	private String convenioTipo;
	
	@Column(name = "convenio_descricao")
	private String convenioDescricao;
	
}
