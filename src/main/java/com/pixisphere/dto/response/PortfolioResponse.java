package com.pixisphere.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PortfolioResponse {
    private Long id;
    private String imageUrl;
    private String description;
    private Integer displayOrder;
}
