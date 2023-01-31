package com.kreyzon.prospectfinder.api.business.service;

import com.kreyzon.prospectfinder.api.business.model.Session;
import com.kreyzon.prospectfinder.api.business.request.SessionRequest;
import com.kreyzon.prospectfinder.api.business.response.LoginResponse;
import com.kreyzon.prospectfinder.api.business.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class SessionService extends BaseService {
    @Autowired
    private LoginService loginService;

    @Autowired
    private RestTemplate restTemplate;

    public Session startSession(SessionRequest sessionRequest) {
        HttpHeaders httpHeaders = HttpUtils.generateHttpHeaders(loginService.login());

        HttpEntity<SessionRequest> requestHttpEntity = new HttpEntity<>(sessionRequest, httpHeaders);

        ResponseEntity<Session> response = null;
        try {
            response = restTemplate
                    .postForEntity(
                            "https://app.xemailextractor.com/api/client/",
                            requestHttpEntity,
                            Session.class
                    );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response.getBody();
    }
}
