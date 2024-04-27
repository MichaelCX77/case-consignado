package com.consignadocontrato.v1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consignadocontrato.v1.model.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {

	Optional<Contrato> findByCodigoSimulacao(String codigoSimulacao);

	Optional<Contrato> findByCodigo(String codigo);

}
