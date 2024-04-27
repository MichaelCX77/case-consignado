package com.consignadocliente.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consignadocliente.v1.model.Convenio;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, String>{

}