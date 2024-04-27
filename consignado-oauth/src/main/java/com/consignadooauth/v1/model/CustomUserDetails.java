package com.consignadooauth.v1.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.consignadooauth.v1.model.authenticate.RoleAuthenticateDTO;
import com.consignadooauth.v1.model.authenticate.UserAuthenticateDTO;

public class CustomUserDetails implements UserDetails,Serializable{

	private static final long serialVersionUID = 1L;

	private UserAuthenticateDTO userAuthenticateDTO;
	
    public CustomUserDetails(UserAuthenticateDTO userAuthenticateDTO) {
        this.userAuthenticateDTO = userAuthenticateDTO;
    }

	public String getEmail() {
		return userAuthenticateDTO.getEmail();
	}
	public void setEmail(String email) {
		this.userAuthenticateDTO.setEmail(email);
	}
	public String getPassword() {
		return userAuthenticateDTO.getPassword();
	}
	public void setPassword(String password) {
		this.userAuthenticateDTO.setPassword(password);
	}

	public Set<RoleAuthenticateDTO> getRoles() {
		return userAuthenticateDTO.getRoles();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return userAuthenticateDTO.getRoles().stream().map(x -> new SimpleGrantedAuthority(x.getRoleName()))
				.collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return this.userAuthenticateDTO.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
