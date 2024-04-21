package com.consignadoregister.service;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.consignadoregister.exception.ResourceNotFoundException;
import com.consignadoregister.model.Role;
import com.consignadoregister.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;
	
	public List<Role> findAll(){
		
		return repository.findAll();

	}
	
    public Role findById(Long id) {
    	
    	Optional<Role> roleOpt = repository.findById(id);
    	
        if (roleOpt.isPresent()) {
        	return roleOpt.get();
        } 
           
        throw new ResourceNotFoundException("Role não encontrada com o ID: " + id, HttpStatus.NOT_FOUND);
    }

	
	public Role findByRoleName(String roleName) {
	
		return repository.findByRoleName(roleName);
	}
	
	public Role save(String nameRole) {
		
		String prefix = "ROLE_";
		String newRole = prefix.concat(nameRole.toUpperCase());

		if(findByRoleName(newRole) == null) {
			return repository.save(new Role(newRole));
		}
		
		throw new ConstraintViolationException("Role já existe",null);
		
	}

}
