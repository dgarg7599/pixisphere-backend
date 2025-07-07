package com.pixisphere.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdminVerificationRequest {

    @NotNull(message = "Approval decision is required")
    private Boolean approved;

    private String comment;
}
