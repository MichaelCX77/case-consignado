package com.consignadoregister.v1.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserWithRolesDTO {

    private Long id;
    private String name;
    private String email;
    private Set<RoleDTO> roles;

}