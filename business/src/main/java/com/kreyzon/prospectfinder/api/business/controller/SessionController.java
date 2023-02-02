package com.kreyzon.prospectfinder.api.business.controller;

import com.kreyzon.prospectfinder.api.business.model.Session;
import com.kreyzon.prospectfinder.api.business.request.SessionRequest;
import com.kreyzon.prospectfinder.api.business.service.LoginService;
import com.kreyzon.prospectfinder.api.business.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prospectfinder/api/v1/business/session")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @GetMapping("/{externalSessionId}")
    public ResponseEntity<Session> info(@PathVariable("externalSessionId") String externalSessionId) {
        return ResponseEntity.ok(sessionService.getSessionInfo(externalSessionId));
    }

    @PostMapping
    public ResponseEntity<Session> start(@RequestBody SessionRequest sessionRequest) {
        return ResponseEntity.ok(sessionService.startSession(sessionRequest));
    }
}
