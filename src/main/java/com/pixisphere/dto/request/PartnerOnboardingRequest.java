package com.pixisphere.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class PartnerOnboardingRequest {

    @NotBlank(message = "Full Name Cannot be Empty")
    @Size(min = 3, message = "Full Name must be MIN 3 Character Long")
    @Pattern(
            regexp = "^[A-Z][a-zA-Z]*$",
            message = "Name must start with a capital letter and contain only alphabets"
    )
    private String fullName;

    @NotBlank(message = "Service category is required")
    private String serviceCategory;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Aadhar number is required")
    private String aadharNumber;

    private List<String> samplePortfolioUrls;
}
