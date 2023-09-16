package com.synchrony.rest.api.util;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synchrony.rest.api.exception.ResourceNotFoundException;

public class ApiUtil {

	public static Optional<String> jsonParser(ResponseEntity<String> responseEntity, String value) {
		Optional<String> imageLink = null;
		try {
			JsonNode jsonNode = new ObjectMapper().readValue(responseEntity.getBody(), JsonNode.class);
			return Optional.of(Optional.of(jsonNode.findValuesAsText(value).toString())
					.orElseThrow(() -> new ResourceNotFoundException("Parse error.")));
			
		} catch (JsonProcessingException e) {
			new ResourceNotFoundException("JSON Read Error.");
		}
		return imageLink;
	}
	
	public static String removeBrackets(String  linkUrl) {
		return linkUrl.substring(1, linkUrl.length()-1);
	}
	
	public static String formatFileTargetPath(String linkUrl) {
		return AppConstants.IMAGE_DOWNLOAD_PATH + linkUrl.substring(linkUrl.lastIndexOf("/")+1);

	}

}
