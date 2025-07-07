package com.pixisphere.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PortfolioRequest {

    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    private String description;

    private Integer displayOrder;
}
