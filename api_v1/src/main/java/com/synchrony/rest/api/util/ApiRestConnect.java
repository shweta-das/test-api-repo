package com.synchrony.rest.api.util;

import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.synchrony.rest.api.exception.ResourceNotFoundException;

public class ApiRestConnect {

	public static Optional<String> connectImgurRestTemplate(String token, String inputUrl) {

		ResponseEntity<String> responseEntity = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.AUTHORIZATION, token);
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
			HttpEntity requestEntity = new HttpEntity<>(requestBody, headers);

			responseEntity = restTemplate.exchange(inputUrl, HttpMethod.GET, requestEntity, String.class);
			
		} catch (Exception e) {
			new ResourceNotFoundException ("Error!, Please try again");
		}
		return Optional.of(ApiUtil.jsonParser(responseEntity, AppConstants.JSON_LINK))
				.orElseThrow(() -> new ResourceNotFoundException("Image Link from " + inputUrl+ " not found"));
		
	}
}
