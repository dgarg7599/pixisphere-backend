package com.pixisphere.service;

import com.pixisphere.dto.request.InquiryRequest;
import com.pixisphere.dto.response.ApiResponse;
import com.pixisphere.dto.response.InquiryResponse;
import com.pixisphere.entity.AppUser;
import com.pixisphere.entity.Inquiry;
import com.pixisphere.entity.InquiryStatus;
import com.pixisphere.entity.Role;
import com.pixisphere.repository.InquiryRepository;
import com.pixisphere.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InquiryService implements IInquiryService {

    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<ApiResponse<String>> createInquiry(InquiryRequest request, Principal principal) {
        AppUser client = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        List<AppUser> partners = userRepository.findAll().stream()
                .filter(u -> u.getRole() == Role.PARTNER)
                .limit(3)
                .collect(Collectors.toList());

        Inquiry inquiry = Inquiry.builder()
                .category(request.getCategory())
                .city(request.getCity())
                .budget(request.getBudget())
                .eventDate(LocalDate.parse(request.getEventDate()))
                .referenceImageUrl(request.getReferenceImageUrl())
                .status(InquiryStatus.NEW)
                .client(client)
                .assignedPartners(partners)
                .build();

        inquiryRepository.save(inquiry);

        return ResponseEntity.ok(ApiResponse.<String>builder()
                .message("Inquiry created and assigned to partners")
                .data("success")
                .build());
    }

    @Override
    public ResponseEntity<ApiResponse<List<InquiryResponse>>> getAssignedLeads(Principal principal) {
        AppUser partner = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Partner not found"));

        List<Inquiry> inquiries = inquiryRepository.findByAssignedPartnersContaining(partner);

        List<InquiryResponse> responses = inquiries.stream().map(inquiry -> InquiryResponse.builder()
                .id(inquiry.getId())
                .category(inquiry.getCategory())
                .city(inquiry.getCity())
                .budget(inquiry.getBudget())
                .eventDate(inquiry.getEventDate().toString())
                .referenceImageUrl(inquiry.getReferenceImageUrl())
                .status(inquiry.getStatus())
                .clientEmail(inquiry.getClient().getEmail())
                .build()).collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.<List<InquiryResponse>>builder()
                .message("Assigned inquiries fetched")
                .data(responses)
                .build());
    }
}
