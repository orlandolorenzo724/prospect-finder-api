package com.kreyzon.prospectfinder.api.business.service;

import com.kreyzon.prospectfinder.api.business.model.Setup;
import org.springframework.stereotype.Service;

@Service
public class SetupService extends BaseService {

    public Setup getSetup() {
        return setupRepository.findAll().get(0);
    }
}
