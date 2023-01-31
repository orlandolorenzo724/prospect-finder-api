package com.kreyzon.prospectfinder.api.business.request;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SessionRequest {
    private String name;
    private String scrape_info;
    private String scrape_type;
    private String maximum_emails;
}
