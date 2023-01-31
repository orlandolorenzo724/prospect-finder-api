package com.kreyzon.prospectfinder.api.business.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "session")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {
    @Id
    @Column(name = "session_id")
    private Integer id;

    private String userId;

    private String name;

    private String scrapeInfo;

    private String scrapeInfoFile;

    private String scrapeType;

    private Integer maximumEmails;

    private Integer scrapedEmails;

    private String downloadSheet;

    private String status;

    private String internalUserId;
}