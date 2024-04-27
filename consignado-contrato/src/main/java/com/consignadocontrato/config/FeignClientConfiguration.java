package com.consignadocontrato.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.consignadocontrato.exception.FeignClientException;
import com.consignadocontrato.util.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;

@Configuration
public class FeignClientConfiguration {

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder(objectMapper);
    }

    public class CustomErrorDecoder implements ErrorDecoder {

        private final ObjectMapper objectMapper;

        public CustomErrorDecoder(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }
        
        @Override
        public Exception decode(String methodKey, Response response) {
            ErrorResponse errorResponse = new ErrorResponse();

            try {
                if (response.body() != null) {
                    Reader reader = new InputStreamReader(response.body().asInputStream(), StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(reader);
					String responseBody = bufferedReader.lines().collect(Collectors.joining("\n"));
                    errorResponse = objectMapper.readValue(responseBody, ErrorResponse.class);
                } else {
                	throw new InternalError("Não foi possível ler o corpo da resposta");
                }
            } catch (IOException e) {
                throw new InternalError("Não foi possível ler o corpo da resposta");
            }

            return new FeignClientException(errorResponse);
        }

    }
}