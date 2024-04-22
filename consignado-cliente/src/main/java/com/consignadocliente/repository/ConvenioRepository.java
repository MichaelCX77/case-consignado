package com.consignadocliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consignadocliente.model.Convenio;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, String>{

}