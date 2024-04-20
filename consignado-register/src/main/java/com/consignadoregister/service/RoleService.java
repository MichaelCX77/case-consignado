package com.consignadoregister.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consignadoregister.entity.Role;
import com.consignadoregister.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;
	
	public List<Role> findAll(){
		
		return repository.findAll();
		
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

	public void delete(Long id) {
		
		repository.deleteById(id);
		
	}
	
}
