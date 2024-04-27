package com.consignadocontrato.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consignadocontrato.model.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {

	Optional<Contrato> findByCodigoSimulacao(String codigoSimulacao);

	Optional<Contrato> findByCodigo(String codigo);

}
