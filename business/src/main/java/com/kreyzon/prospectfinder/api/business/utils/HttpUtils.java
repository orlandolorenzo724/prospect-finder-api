package com.kreyzon.prospectfinder.api.business.utils;


import org.springframework.http.HttpHeaders;

public class HttpUtils {

    public static HttpHeaders generateHttpHeaders(String token) {
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.set("Authorization", "Token " + token);
        headers.set("Content-Type", "application/json");

        return headers;
    }
}
