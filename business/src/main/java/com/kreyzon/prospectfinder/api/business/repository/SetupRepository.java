package com.kreyzon.prospectfinder.api.business.repository;

import com.kreyzon.prospectfinder.api.business.model.Setup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetupRepository extends JpaRepository<Setup, Integer> {
}