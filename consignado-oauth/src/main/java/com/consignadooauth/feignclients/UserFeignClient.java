package com.consignadooauth.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.consignadooauth.entity.User;

@Component
@FeignClient(name = "consignado-register", path="/users")
public interface UserFeignClient {

	@GetMapping("/search")
	public ResponseEntity<User> findByEmail(@RequestParam String email);
	
}