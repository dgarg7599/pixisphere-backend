package com.pixisphere.dto.response;

import com.pixisphere.entity.InquiryStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InquiryResponse {

    private Long id;
    private String category;
    private String city;
    private Double budget;
    private String eventDate;
    private String referenceImageUrl;
    private InquiryStatus status;
    private String clientEmail;
}
