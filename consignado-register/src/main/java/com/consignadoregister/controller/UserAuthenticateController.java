package com.consignadoregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.consignadoregister.model.User;
import com.consignadoregister.model.authenticate.UserAuthenticateDTO;
import com.consignadoregister.service.UserService;

@RestController
@RequestMapping(value = "/userauthenticate")
public class UserAuthenticateController {

	@Autowired
	private UserService service;
	
	@GetMapping("/search")
	public ResponseEntity<UserAuthenticateDTO> getUserAuthenticate(@RequestParam(required = false) String email) {

		User user = service.findUserByEmailAuthenticate(email);
		return ResponseEntity.ok(new UserAuthenticateDTO(user));
	}
	
}
