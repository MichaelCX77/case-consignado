package com.consignadosimulacao.v1.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Simulacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "simulacao_id")
	private Long id;
	
	@Column(name = "simulacao_codigo")
	private String codigo;
	
	@Column(name = "simulacao_data")
	private LocalDateTime data;
	
	@Column(name = "simulacao_cliente_cpf")
	private String cpf;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "taxa_convenio_tipo")
	private TaxaConvenio taxaTipo;
	
	@Column(name = "taxa_convenio_porcentagem")
	private String taxaPorcentagem;
	
	@Column(name = "simulacao_valor_solicitado")
	private Double vlrSolicicado;
	
	@Column(name = "simulacao_valor_total")
	private Double vlrTotal;
	
	@Column(name = "simulacao_valor_parcela")
	private Double vlrParcela;
	
	@Column(name = "simulacao_quantidade_parcela")
	private Integer qtdParcelas;

}
