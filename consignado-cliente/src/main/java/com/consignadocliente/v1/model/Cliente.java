package com.consignadocliente.v1.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.consignadocliente.util.EnumCorrentista;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cliente_id")
	private Long id;
	
	@Column(name = "cliente_cpf", unique = true)
	private String cpf;
	
	@Column(name = "cliente_nome")
	private String nome;
	
	@Column(name = "cliente_correntista")
	@Enumerated(EnumType.STRING)
	private EnumCorrentista isCorrentista;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "segmento_tipo")
	private Segmento segmento;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "convenio_tipo")
	private Convenio convenio;
	
}
