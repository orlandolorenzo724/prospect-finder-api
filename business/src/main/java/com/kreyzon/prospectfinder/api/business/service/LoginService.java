package com.kreyzon.prospectfinder.api.business.service;

import com.kreyzon.prospectfinder.api.business.model.Setup;
import com.kreyzon.prospectfinder.api.business.request.LoginRequest;
import com.kreyzon.prospectfinder.api.business.response.LoginResponse;
import com.kreyzon.prospectfinder.api.business.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {
    @Autowired
    private SetupService setupService;

    @Autowired
    @Qualifier("outside")
    private RestTemplate restTemplate;

    public String login() {
        Setup setup = setupService.getSetup();

        HttpHeaders httpHeaders = HttpUtils.generateHttpHeaders("");

        LoginRequest loginRequest = LoginRequest
                .builder()
                .username(setup.getUsername())
                .password(setup.getPassword())
                .build();

        HttpEntity<LoginRequest> requestHttpEntity = new HttpEntity<LoginRequest>(loginRequest, httpHeaders);

        ResponseEntity<LoginResponse> response = null;
        try {
            response = restTemplate
                    .postForEntity(
                            "https://app.xemailextractor.com/auth-token/",
                            requestHttpEntity,
                            LoginResponse.class
                    );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response.getBody().getToken();
    }
}
