package com.consignadoregister.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

	private Long id;
	
	@NotBlank(message = "O nome é obrigatório")
	private String name;
	
	@NotBlank(message = "A senha é obrigatória")
	private String password;
	
	@Email(message = "Formato de e-mail inválido")
	@NotBlank(message = "O email é obrigatório")
	private String email;
	
	public UserDTO (User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}

}