package com.consignadoregister.model.authenticate;

import java.util.Set;
import java.util.stream.Collectors;

import com.consignadoregister.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthenticateDTO {
	
	private String email;
	
	private String password;
	
	private Set<RoleAuthenticateDTO> roles;
	
	public UserAuthenticateDTO (User user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
        this.roles = user.getRoles().stream()
                .map(role -> new RoleAuthenticateDTO(role.getRoleName()))
                .collect(Collectors.toSet());
		
	}
	
}
