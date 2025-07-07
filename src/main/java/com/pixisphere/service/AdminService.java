package com.pixisphere.service;

import com.pixisphere.dto.response.AdminStatsResponse;
import com.pixisphere.dto.response.ApiResponse;
import com.pixisphere.entity.PartnerStatus;
import com.pixisphere.entity.Role;
import com.pixisphere.repository.InquiryRepository;
import com.pixisphere.repository.PartnerRepository;
import com.pixisphere.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final PartnerRepository partnerRepository;
    private final InquiryRepository inquiryRepository;

    public ResponseEntity<ApiResponse<AdminStatsResponse>> getStats() {
        long clients = userRepository.countByRole(Role.CLIENT);
        long partners = userRepository.countByRole(Role.PARTNER);
        long pending = partnerRepository.countByStatus(PartnerStatus.PENDING);
        long inquiries = inquiryRepository.count();

        AdminStatsResponse stats = AdminStatsResponse.builder()
                .totalClients(clients)
                .totalPartners(partners)
                .pendingVerifications(pending)
                .totalInquiries(inquiries)
                .build();

        return ResponseEntity.ok(ApiResponse.<AdminStatsResponse>builder()
                .message("Admin dashboard stats")
                .data(stats)
                .build());
    }
}