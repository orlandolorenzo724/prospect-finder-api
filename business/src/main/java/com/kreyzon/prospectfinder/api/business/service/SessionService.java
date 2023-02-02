package com.kreyzon.prospectfinder.api.business.service;

import com.kreyzon.prospectfinder.api.business.model.Session;
import com.kreyzon.prospectfinder.api.business.request.SessionRequest;
import com.kreyzon.prospectfinder.api.business.response.LoginResponse;
import com.kreyzon.prospectfinder.api.business.response.SessionResponse;
import com.kreyzon.prospectfinder.api.business.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Locale;


@Service
public class SessionService extends BaseService {
    @Autowired
    private LoginService loginService;

    @Autowired
    private RestTemplate restTemplate;

    public Session startSession(SessionRequest sessionRequest) {
        HttpHeaders httpHeaders = HttpUtils.generateHttpHeaders(loginService.login());

        HttpEntity<SessionRequest> requestHttpEntity = new HttpEntity<>(sessionRequest, httpHeaders);

        ResponseEntity<SessionResponse> response = null;
        try {
            response = restTemplate
                    .postForEntity(
                            "https://app.xemailextractor.com/api/client/",
                            requestHttpEntity,
                            SessionResponse.class
                    );
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalStateException("Error during the start of the session \n" + ex.getMessage());
        }

        Session session = convertSessionResponseToSession(response.getBody(), sessionRequest.getUserId());
        sessionRepository.save(session);

        return session;
    }

    public Session getSessionInfo(String externalSessionId) {
        if (sessionRepository.findByExternalSessionId(externalSessionId).isEmpty())
            throw new IllegalArgumentException("Id " + externalSessionId + " not found");

        HttpHeaders httpHeaders = HttpUtils.generateHttpHeaders(loginService.login());

        HttpEntity<SessionResponse> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<SessionResponse[]> response = null;
        try {
            response = restTemplate
                    .exchange(
                            "https://app.xemailextractor.com/api/client/",
                            HttpMethod.GET,
                            entity,
                            SessionResponse[].class
                    );
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalStateException("Error during the retrieving of the session \n" + ex.getMessage());
        }

        Session session = convertSessionResponseToSession(response.getBody()[0], "NR");
        return session;
    }

    private Session convertSessionResponseToSession(SessionResponse response, String userId) {
        if (response.getMaximum_emails() == null)
            response.setMaximum_emails("0");
        if (response.getScraped_emails() == null)
            response.setScraped_emails("0");

        Session session = Session
                .builder()
                .id(generateId())
                .userId(userId)
                .name(response.getName())
                .scrapeInfo(response.getScrape_info())
                .scrapeInfoFile(response.getScrape_info_file())
                .scrapeType(response.getScrape_type())
                .maximumEmails(Integer.valueOf(response.getMaximum_emails()))
                .scrapedEmails(Integer.valueOf(response.getScraped_emails()))
                .downloadSheet(response.getDownload_sheet())
                .status(response.getStatus())
                .externalUserId(String.valueOf(response.getUser()))
                .externalSessionId(response.getId())
                .creationDate(LocalDateTime.now())
                .build();

        return session;
    }

    private Integer generateId() {
        Integer id = sessionRepository.getLastId();
        if (id == null)
            return 1;

        return id += 1;
    }
}
