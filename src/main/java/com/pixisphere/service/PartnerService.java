package com.pixisphere.service;

import com.pixisphere.dto.request.AdminVerificationRequest;
import com.pixisphere.dto.request.PartnerOnboardingRequest;
import com.pixisphere.dto.response.ApiResponse;
import com.pixisphere.dto.response.PartnerProfileResponse;
import com.pixisphere.entity.AppUser;
import com.pixisphere.entity.Partner;
import com.pixisphere.entity.PartnerStatus;
import com.pixisphere.repository.PartnerRepository;
import com.pixisphere.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerService implements IPartnerService {

    private final PartnerRepository partnerRepository;
    private final UserRepository appUserRepository;

    @Override
    public ResponseEntity<ApiResponse<String>> submitOnboarding(Long userId, PartnerOnboardingRequest request) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (partnerRepository.findByUserId(userId).isPresent()) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.<String>builder().message("Partner profile already exists").data(null).build()
            );
        }

        Partner partner = Partner.builder()
                .fullName(request.getFullName())
                .serviceCategory(request.getServiceCategory())
                .city(request.getCity())
                .aadharNumber(request.getAadharNumber())
                .samplePortfolioUrls(request.getSamplePortfolioUrls())
                .status(PartnerStatus.PENDING)
                .user(user)
                .build();

        partnerRepository.save(partner);

        return ResponseEntity.ok(
                ApiResponse.<String>builder().message("Partner onboarding submitted").data("pending").build()
        );
    }

    @Override
    public ResponseEntity<ApiResponse<List<PartnerProfileResponse>>> getPendingVerifications() {
        List<Partner> pendingList = partnerRepository.findByStatus(PartnerStatus.PENDING);

        List<PartnerProfileResponse> response = pendingList.stream().map(p -> PartnerProfileResponse.builder()
                .id(p.getId())
                .fullName(p.getFullName())
                .city(p.getCity())
                .serviceCategory(p.getServiceCategory())
                .email(p.getUser().getEmail())
                .portfolioUrls(p.getSamplePortfolioUrls())
                .build()).collect(Collectors.toList());

        return ResponseEntity.ok(
                ApiResponse.<List<PartnerProfileResponse>>builder()
                        .message("Pending partner verifications")
                        .data(response)
                        .build()
        );
    }

    @Override
    public ResponseEntity<ApiResponse<String>> verifyPartner(Long partnerId, AdminVerificationRequest request) {
        Partner partner = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new RuntimeException("Partner not found"));

        if (request.getApproved()) {
            partner.setStatus(PartnerStatus.VERIFIED);
            partner.setVerificationComment("Approved: " + request.getComment());
        } else {
            partner.setStatus(PartnerStatus.REJECTED);
            partner.setVerificationComment("Rejected: " + request.getComment());
        }

        partnerRepository.save(partner);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .message("Partner status updated")
                        .data(partner.getStatus().name())
                        .build()
        );
    }
}
