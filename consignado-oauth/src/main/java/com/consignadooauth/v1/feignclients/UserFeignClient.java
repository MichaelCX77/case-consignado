package com.consignadooauth.v1.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.consignadooauth.v1.model.authenticate.UserAuthenticateDTO;

@Component
@FeignClient(name = "consignado-register", path="/api/v1/userauthenticate")
public interface UserFeignClient {

	@GetMapping("/search")
	public ResponseEntity<UserAuthenticateDTO> search(@RequestParam String email);
	
}