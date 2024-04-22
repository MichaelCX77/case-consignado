package com.consignadocliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consignadocliente.model.Segmento;

@Repository
public interface SegmentoRepository extends JpaRepository<Segmento, String>{

}