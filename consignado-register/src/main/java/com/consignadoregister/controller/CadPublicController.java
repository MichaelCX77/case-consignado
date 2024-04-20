package com.consignadoregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consignadoregister.entity.User;
import com.consignadoregister.service.UserService;

@RestController
@RequestMapping(value = "/pub/create")
public class CadPublicController {

	@Autowired
	private UserService service;
	
	@PutMapping(value ="/user")
	public ResponseEntity<User> saveUser(User user){
		
		if (user.getPassword().isBlank()) {
			return ResponseEntity.badRequest().build();
		}
		
		user.setId(null);
		return ResponseEntity.ok(service.save(user));
		
	}
}
