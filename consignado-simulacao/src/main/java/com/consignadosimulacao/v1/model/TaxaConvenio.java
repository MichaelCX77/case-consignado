package com.consignadosimulacao.v1.model;

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
public class TaxaConvenio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "taxa_convenio_tipo", unique = true)
	private String taxaConvenioTipo;
	
	@Column(name = "taxa_convenio_porcentagem")
	private Double taxaConvenioPorcentagem;

}
