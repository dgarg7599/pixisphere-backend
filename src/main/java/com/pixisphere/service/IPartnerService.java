package com.pixisphere.service;

import com.pixisphere.dto.request.AdminVerificationRequest;
import com.pixisphere.dto.request.PartnerOnboardingRequest;
import com.pixisphere.dto.response.ApiResponse;
import com.pixisphere.dto.response.PartnerProfileResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPartnerService {

    ResponseEntity<ApiResponse<String>> submitOnboarding(Long userId, PartnerOnboardingRequest request);

    ResponseEntity<ApiResponse<List<PartnerProfileResponse>>> getPendingVerifications();

    ResponseEntity<ApiResponse<String>> verifyPartner(Long partnerId, AdminVerificationRequest request);
}
