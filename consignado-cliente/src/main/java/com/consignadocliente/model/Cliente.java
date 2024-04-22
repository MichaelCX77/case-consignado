package com.consignadocliente.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

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
	private long clienteId;
	
	@Column(name = "cliente_cpf", unique = true)
	private String clienteCpf;
	
	@Column(name = "cliente_nome")
	private String clienteNome;
	
	@Column(name = "cliente_correntista")
	@NotNull
	private Character clienteCorrentista;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name= "ClienteSegmento",
			joinColumns = @JoinColumn(name = "cliente_segmento_tipo"),
			inverseJoinColumns = @JoinColumn(name = "segmento_tipo"))
	private Segmento clienteSegmentoTipo;
	
	@Column(name = "cliente_convenio_tipo")
	@JoinTable(name= "ClienteConvenio",
			joinColumns = @JoinColumn(name = "cliente_convenio_tipo"),
			inverseJoinColumns = @JoinColumn(name = "convenio_tipo"))
	private Convenio clienteConvenioTipo;
	
}
