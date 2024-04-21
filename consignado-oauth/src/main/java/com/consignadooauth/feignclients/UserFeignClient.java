package com.consignadooauth.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.consignadooauth.model.authenticate.UserAuthenticateDTO;

@Component
@FeignClient(name = "consignado-register", path="/userauthenticate")
public interface UserFeignClient {

	@GetMapping
	public ResponseEntity<UserAuthenticateDTO> search(@RequestParam String email);
	
}