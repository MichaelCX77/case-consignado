package com.consignadooauth.v1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.consignadooauth.exception.ResourceNotFoundException;
import com.consignadooauth.v1.feignclients.UserFeignClient;
import com.consignadooauth.v1.model.CustomUserDetails;
import com.consignadooauth.v1.model.authenticate.UserAuthenticateDTO;


@Service
public class UserAuthenticateService implements UserDetailsService {

	@Autowired
	private UserFeignClient userFeignClient;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserAuthenticateDTO userAuthenticateDTO = userFeignClient.search(username).getBody();
		if(userAuthenticateDTO == null) {
			throw new ResourceNotFoundException("User not found: " + username, HttpStatus.NOT_FOUND);
		}
		CustomUserDetails user = new CustomUserDetails(userAuthenticateDTO);
		return user;
	}
	
}
