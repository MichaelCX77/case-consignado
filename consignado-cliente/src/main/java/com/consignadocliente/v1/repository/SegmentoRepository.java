package com.consignadocliente.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consignadocliente.v1.model.Segmento;

@Repository
public interface SegmentoRepository extends JpaRepository<Segmento, String>{

}