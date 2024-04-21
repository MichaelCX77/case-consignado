package com.consignadooauth.model.authenticate;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.consignadooauth.model.CustomUserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthenticateDTO {
	
	@NotBlank(message = "A senha é obrigatória")
	private String password;
	
	@Email(message = "Formato de e-mail inválido")
	private String email;
	
	private Set<RoleAuthenticateDTO> roles;
	
	public UserAuthenticateDTO (CustomUserDetails user) {
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.roles = user.getRoles();
	}

}