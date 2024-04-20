package com.consignadoregister.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consignadoregister.entity.Role;
import com.consignadoregister.service.RoleService;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {


	@Autowired
	private RoleService service;
	
	@GetMapping
	public ResponseEntity<List<Role>> getAllRoles(){
		
		return ResponseEntity.ok(service.findAll());
		
	}
	
	@PutMapping(value= "/create")
	public ResponseEntity<Role> createNewRole(Role role){
		
		String roleName = role.getRoleName();
		
		if(roleName != null && roleName.trim() != "") {
			return ResponseEntity.ok(service.save(role.getRoleName()));
		}
		
		throw new NullPointerException("Nome da role não pode ser nulo");
		
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteRole(@PathVariable Long id){
		
		service.delete(id);
		
		return ResponseEntity.ok(null);
	}
}
