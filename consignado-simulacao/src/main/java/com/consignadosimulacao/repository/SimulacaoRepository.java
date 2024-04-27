package com.consignadosimulacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consignadosimulacao.model.Simulacao;

@Repository
public interface SimulacaoRepository extends JpaRepository<Simulacao, Long>{

	Optional<Simulacao> findByCodigo(String codigo);


}