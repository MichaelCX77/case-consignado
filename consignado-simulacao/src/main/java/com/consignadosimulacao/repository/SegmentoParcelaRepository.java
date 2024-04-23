package com.consignadosimulacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consignadosimulacao.model.SegmentoParcela;
import com.consignadosimulacao.model.SegmentoParcelaDTO;

@Repository
public interface SegmentoParcelaRepository extends JpaRepository<SegmentoParcela, String>{

	Optional<SegmentoParcelaDTO> findBySegmentoParcelaTipo(String segmentoParcelaTipo);


}