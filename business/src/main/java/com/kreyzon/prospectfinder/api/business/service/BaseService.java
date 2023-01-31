package com.kreyzon.prospectfinder.api.business.service;

import com.kreyzon.prospectfinder.api.business.repository.SessionRepository;
import com.kreyzon.prospectfinder.api.business.repository.SetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    @Autowired
    protected SessionRepository sessionRepository;

    @Autowired
    protected SetupRepository setupRepository;
}
