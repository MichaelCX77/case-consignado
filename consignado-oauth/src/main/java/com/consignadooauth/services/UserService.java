package com.consignadooauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.consignadooauth.entity.User;
import com.consignadooauth.feignclients.UserFeignClient;


@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserFeignClient userFeignClient;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userFeignClient.findByEmail(username).getBody();
		if(user == null) {
			throw new UsernameNotFoundException("Email not found");
		}
		System.out.println(user);
		return user;
	}
	
}
