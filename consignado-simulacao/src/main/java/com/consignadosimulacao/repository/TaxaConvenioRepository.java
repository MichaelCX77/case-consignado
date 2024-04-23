package com.consignadosimulacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consignadosimulacao.model.TaxaConvenio;
import com.consignadosimulacao.model.TaxaConvenioDTO;

@Repository
public interface TaxaConvenioRepository extends JpaRepository<TaxaConvenio, String>{

	Optional<TaxaConvenioDTO> findByTaxaConvenioTipo(String taxaConvenioTipo);


}