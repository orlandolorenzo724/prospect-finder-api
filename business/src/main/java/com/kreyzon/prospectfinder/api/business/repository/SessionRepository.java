package com.kreyzon.prospectfinder.api.business.repository;

import com.kreyzon.prospectfinder.api.business.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
}