package com.consignadosimulacao.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseCalculo {

	private boolean isCorrentista;
	private Double taxa;
	private Integer qtdParcelasDesejada;
	private Double vlrSolicitado;
}
