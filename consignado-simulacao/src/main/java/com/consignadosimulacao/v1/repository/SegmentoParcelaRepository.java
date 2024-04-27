package com.consignadosimulacao.v1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consignadosimulacao.v1.model.SegmentoParcela;

@Repository
public interface SegmentoParcelaRepository extends JpaRepository<SegmentoParcela, String>{

	Optional<SegmentoParcela> findBySegmentoParcelaTipo(String segmentoParcelaTipo);


}