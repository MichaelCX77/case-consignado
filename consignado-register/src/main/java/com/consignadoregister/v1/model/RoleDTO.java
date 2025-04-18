package com.consignadoregister.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO{
	
	private Long id;
	
	private String roleName;
	
	public RoleDTO(Role role) {
		this.id = role.getId();
		this.roleName = role.getRoleName();
	}
	
}
