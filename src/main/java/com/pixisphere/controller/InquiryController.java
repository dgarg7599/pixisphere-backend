package com.pixisphere.controller;

import com.pixisphere.dto.request.InquiryRequest;
import com.pixisphere.dto.response.ApiResponse;
import com.pixisphere.dto.response.InquiryResponse;
import com.pixisphere.service.IInquiryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InquiryController {

    private final IInquiryService inquiryService;

    @PostMapping("/client/inquiry")
    public ResponseEntity<ApiResponse<String>> createInquiry(@Valid @RequestBody InquiryRequest request,
                                                             Principal principal) {
        return inquiryService.createInquiry(request, principal);
    }

    @GetMapping("/partner/leads")
    public ResponseEntity<ApiResponse<List<InquiryResponse>>> getAssignedLeads(Principal principal) {
        return inquiryService.getAssignedLeads(principal);
    }
}
