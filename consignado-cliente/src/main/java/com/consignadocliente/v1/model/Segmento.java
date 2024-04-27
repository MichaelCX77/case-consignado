package com.consignadocliente.v1.model;

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
public class Segmento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "segmento_tipo")
	private String segmentoTipo;
	
	@Column(name = "segmento_descricao")
	private String segmentoDescricao;
	
}
