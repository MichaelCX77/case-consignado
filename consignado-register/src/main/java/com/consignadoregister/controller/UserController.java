package com.consignadoregister.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consignadoregister.model.Role;
import com.consignadoregister.model.RoleDTO;
import com.consignadoregister.model.User;
import com.consignadoregister.model.UserDTO;
import com.consignadoregister.model.UserWithRolesDTO;
import com.consignadoregister.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers (){
		
		List<User> listUsers = service.findAll();
		return ResponseEntity.ok(convertToUserDTO(listUsers));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> getUserById (@PathVariable Long id){
		
		return ResponseEntity.ok(new UserDTO(service.findById(id)));
	}
	
    @GetMapping("/{userId}/roles")
    public ResponseEntity<UserWithRolesDTO> getUserWithRoles(@PathVariable Long userId) {
    	
    	User user = service.findById(userId);
        return ResponseEntity.ok(convertToUserWithRolesDTO(user));
    }
    
    @GetMapping("/with-roles")
    public ResponseEntity<List<UserWithRolesDTO>> getAllUsersWithRoles() {
    	
    	List<User> listUsers = service.findAll();
    	return ResponseEntity.ok(convertToListUserWithRolesDTO(listUsers));
    }
	
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@Validated @RequestBody UserDTO userDTO) {

		User user = service.save(userDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO(user));
				
				
	}
	
	@PutMapping(value ="/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Validated @RequestBody UserDTO userDTO) {
			
		User user = service.update(id,userDTO);
		return ResponseEntity.ok(new UserDTO(user));
	}
	
	@PutMapping(value ="/{userId}/role")
	public ResponseEntity<UserWithRolesDTO> updateUserRole(@PathVariable Long userId, @RequestBody Set<RoleDTO> listRoleDTO) {
		
		User user = service.updateUserRole(userId,listRoleDTO);
		return ResponseEntity.ok(convertToUserWithRolesDTO(user));

	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		
		service.deleteUser(userId);
		return ResponseEntity.ok().build();
		
	}
	
	@DeleteMapping("/{userId}/role")
	public ResponseEntity<Void> deleteUserRole(@PathVariable Long userId, @RequestBody Set<RoleDTO> listRoleDTO) {
		
		service.deleteUserRole(userId,listRoleDTO);
		return ResponseEntity.ok().build();
		
	}
	
	
	private List<UserWithRolesDTO> convertToListUserWithRolesDTO(List<User> listUsers){
		
		List<UserWithRolesDTO> listUserWithRolesDTO = listUsers.stream()
									.map(this::convertToUserWithRolesDTO)
									.collect(Collectors.toList());;
									
		return	listUserWithRolesDTO;

	}
	
	private UserWithRolesDTO convertToUserWithRolesDTO(User user){
		
		return new UserWithRolesDTO(user.getId(), user.getName(), user.getEmail(),convertToRoleDTO(user.getRoles()));
	}
	
	private Set<RoleDTO> convertToRoleDTO(Set<Role> lisRoles){
		Set<RoleDTO> lisRolesDTO = new HashSet<RoleDTO>();
		
		lisRolesDTO = lisRoles.stream()
				.map(role -> new RoleDTO(role))
				.collect(Collectors.toSet());
		return lisRolesDTO;
	}
	
	private List<UserDTO> convertToUserDTO(List<User> listUsers){
		
		List<UserDTO> listUsersDTO = new ArrayList<UserDTO>();
		
		listUsersDTO = listUsers.stream()
				.map(user -> new UserDTO(user))
				.collect(Collectors.toList());
		return listUsersDTO;
	}
	
	
}
