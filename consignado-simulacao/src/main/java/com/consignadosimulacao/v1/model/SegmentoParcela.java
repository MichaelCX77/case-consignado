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
public class SegmentoParcela implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "segmento_parcela_tipo", unique = true)
	private String segmentoParcelaTipo;
	
	@Column(name = "segmento_parcela_quantidade")
	private Integer parcelaQtd;

}
