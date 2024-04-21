package com.consignadoregister.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consignadoregister.model.Role;
import com.consignadoregister.model.RoleDTO;
import com.consignadoregister.service.RoleService;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {


	@Autowired
	private RoleService service;
	
	@GetMapping
	public ResponseEntity<Set<RoleDTO>> getAllRoles(){
		
		List<Role> lisRoles = service.findAll();
		
		return ResponseEntity.ok(convertToRoleDTO(lisRoles));
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<RoleDTO> getRoleById (@PathVariable Long id){
		
		Role role = service.findById(id);
		return ResponseEntity.ok(new RoleDTO(role));
	}
	
	private Set<RoleDTO> convertToRoleDTO(List<Role> lisRoles){
		Set<RoleDTO> lisRolesDTO = new HashSet<RoleDTO>();
		
		lisRolesDTO = lisRoles.stream()
				.map(role -> new RoleDTO(role))
				.collect(Collectors.toSet());
		return lisRolesDTO;
	}
	
}
