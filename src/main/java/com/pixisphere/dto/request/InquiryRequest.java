package com.pixisphere.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InquiryRequest {

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "City is required")
    private String city;

    @NotNull(message = "Budget is required")
    private Double budget;

    @NotNull(message = "Event date is required (yyyy-MM-dd)")
    private String eventDate;

    private String referenceImageUrl;
}