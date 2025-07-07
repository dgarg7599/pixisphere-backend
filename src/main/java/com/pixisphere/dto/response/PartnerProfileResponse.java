package com.pixisphere.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PartnerProfileResponse {

    private Long id;
    private String fullName;
    private String email;
    private String city;
    private String serviceCategory;
    private List<String> portfolioUrls;
}
