package com.synchrony.rest.api.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Data
@Configuration
@ConfigurationProperties(prefix = "client.imgur")
@Slf4j
public class Imgur {
	
	private String userName;
	private String clientId;
    private String clientSecret;
    private String apiUrl;
    private String authorizeUri;
    private String callbackUri;
    private String tokenUri;
    private String uploadUrl;
    private String accessToken;
    private String refreshToken;
    
    

}