package com.consignadosimulacao.v1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consignadosimulacao.v1.model.TaxaConvenio;

@Repository
public interface TaxaConvenioRepository extends JpaRepository<TaxaConvenio, String>{

	Optional<TaxaConvenio> findByTaxaConvenioTipo(String taxaConvenioTipo);


}