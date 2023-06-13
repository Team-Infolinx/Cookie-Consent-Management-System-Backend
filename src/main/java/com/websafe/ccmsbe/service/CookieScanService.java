package com.websafe.ccmsbe.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CookieScanService {

    public List<String> scanCookies(String websiteUrl) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> responseEntity = restTemplate.getForEntity(websiteUrl, Void.class);

        HttpHeaders headers = responseEntity.getHeaders();
        List<String> cookies = headers.get(HttpHeaders.SET_COOKIE);

        return cookies;
    }
}
