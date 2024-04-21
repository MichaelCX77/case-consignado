package com.consignadoregister.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.consignadoregister.exception.DataIntegrityViolationException;
import com.consignadoregister.exception.ResourceNotFoundException;
import com.consignadoregister.model.Role;
import com.consignadoregister.model.RoleDTO;
import com.consignadoregister.model.User;
import com.consignadoregister.model.UserDTO;
import com.consignadoregister.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleService roleService;
	
	
	public List<User> findAll() {
		
		return repository.findAll();

	}
	
    public User findById(Long id) {
    	
    	Optional<User> userOpt = repository.findById(id);
    	
        if (userOpt.isPresent()) {
        	return userOpt.get();
        } 
           
        throw new ResourceNotFoundException("Usuário não encontrado com o ID: " + id, HttpStatus.NOT_FOUND);
    }


	
	public User findUserByEmail(String email) {
		
		Optional <User> userOpt = repository.findByEmail(email);
		if (userOpt.isPresent()) {
			return userOpt.get();
		}
		
		throw new ResourceNotFoundException("Email não encontrado: " + email, HttpStatus.NOT_FOUND);
		
	}
	
	public User findUserByEmailAuthenticate(String email) {
		
		Optional <User> userOpt = repository.findByEmail(email);
		if (userOpt.isPresent()) {
			return userOpt.get();
		}
		
		throw new ResourceNotFoundException("Email não encontrado: " + email, HttpStatus.NOT_FOUND);
		
	}
	
	public User save(UserDTO userDTO) {
			
		userDTO.setId(null);
		try {
			findUserByEmail(userDTO.getEmail());
		} catch (ResourceNotFoundException e) {
			User user = new User(null, userDTO.getName(), userDTO.getEmail(), bcryptEncoder.encode(userDTO.getPassword()));
			return repository.save(user);
		}
		
		throw new DataIntegrityViolationException("Email já existe: " + userDTO.getEmail(), HttpStatus.BAD_REQUEST);

	}
	
	public User update(Long id,UserDTO userDTO) throws ConstraintViolationException{
		
		User user = findById(id);
		user.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
		user.setName(userDTO.getName());
		user = repository.save(user);
		user.setPassword("your_new_pass");
		
		return user;
		
	}

	public User updateUserRole(Long userId, Set<RoleDTO> listRoleDTO) {
		
		User user = repository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + userId, HttpStatus.NOT_FOUND));;
		
		for (RoleDTO roleDTO : listRoleDTO) {
			Role role = roleService.findById(roleDTO.getId());
			user.getRoles().add(role);
		}
		return repository.save(user);

	}
	
	public void deleteUser(Long id) {
		
		repository.delete(findById(id));
	}

	public void deleteUserRole(Long userId, Set<RoleDTO> listRoleDTO) {
		
		User user = findById(userId);
		
		for (RoleDTO roleDTO : listRoleDTO) {
			roleService.findById(roleDTO.getId());
			user.getRoles().removeIf(useRole -> Long.valueOf(useRole.getId()).equals(roleDTO.getId()));
		}
//		.orElseThrow(() -> new ResourceNotFoundException("Nenhuma Role relacionada a esse usuário com esse id: " + userId, HttpStatus.NOT_FOUND));;
		repository.save(user);
		
	}
	
}
