package com.consignadogateway.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter {

	@Autowired
	private JwtTokenStore tokenStore; 
	
	private static final String[] URL_PUBLIC;
	private static final String[] URL_USER;
	private static final String[] URL_ADMIN;
	
	static {
		
		URL_PUBLIC = new String[]{ 
				"/consignado-oauth/oauth/token",
				"/consignado-register/pub/**"
				};
		
		URL_USER = new String[]{ 
				"/consignado-client/**",
				"/consignado-simulacao/**",
				"/consignado-contrato/**"
				};
		
		URL_ADMIN = new String[]{ 
				"/consignado-register/users/**",
				"/consignado-register/roles/**", 
				};

	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		// hasRole - tem que ter a role
		// hasAnyRole - tem que ter ao menos uma das roles
		
		http.authorizeRequests()	
		.antMatchers(URL_PUBLIC).permitAll()
		.antMatchers(HttpMethod.GET, URL_USER).hasRole("USER")
		.antMatchers(URL_ADMIN).hasRole("ADMIN")
		.anyRequest().authenticated();
		
		http.cors().configurationSource(corsConfigurationSource());
		
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Arrays.asList("*"));
		corsConfig.setAllowedMethods(Arrays.asList("POST","GET","PUT","DELETE","PATCH"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**" , corsConfig);
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		
		FilterRegistrationBean<CorsFilter> bean
		= new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
		
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
		
	}
	
}
