package com.consignadoregister.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.consignadoregister.entity.User;
import com.consignadoregister.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserRepository repository;
	
	public User findById(Long id) {
		
		User user = repository.findById(id).get();
		return user;
	}
	
	public User findByEmail(String email) {
		
		User user = repository.findByEmail(email);
		return user;
	}

	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User save(User user) {
		
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		
		try {
			return repository.save(user);
		} catch (ConstraintViolationException e) {
			throw new ConstraintViolationException("Email já existe",null);
		}
	}
	
	public User update(User user) {
		
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		
		User userDb = repository.findById(user.getId()).get();
		userDb.updateUser(user);
		
		try {
			return repository.save(userDb);
		} catch (ConstraintViolationException e) {
			throw new ConstraintViolationException("Email já existe",null);
		}
	}

	public void delete(Long id) {
		
		repository.delete(repository.findById(id).get());
		
	}
	
}
