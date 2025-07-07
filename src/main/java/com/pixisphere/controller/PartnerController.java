package com.pixisphere.controller;

import com.pixisphere.dto.request.AdminVerificationRequest;
import com.pixisphere.dto.request.PartnerOnboardingRequest;
import com.pixisphere.dto.response.ApiResponse;
import com.pixisphere.dto.response.PartnerProfileResponse;
import com.pixisphere.entity.AppUser;
import com.pixisphere.service.IPartnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PartnerController {

    private final IPartnerService partnerService;

    // Partner submits onboarding
    @PostMapping("/partner/onboarding")
    public ResponseEntity<ApiResponse<String>> submitOnboarding(
            @AuthenticationPrincipal Object principal,
            @Valid @RequestBody PartnerOnboardingRequest request) {

        AppUser user = (AppUser) principal;
        Long userId = user.getId();
        return partnerService.submitOnboarding(userId, request);
    }

    // Admin fetches pending partner list
    @GetMapping("/admin/verifications")
    public ResponseEntity<ApiResponse<List<PartnerProfileResponse>>> getPendingPartners() {
        return partnerService.getPendingVerifications();
    }

    // Admin approves/rejects partner
    @PutMapping("/admin/verify/{partnerId}")
    public ResponseEntity<ApiResponse<String>> verifyPartner(@PathVariable Long partnerId,
                                                             @Valid @RequestBody AdminVerificationRequest request) {
        return partnerService.verifyPartner(partnerId, request);
    }
}
