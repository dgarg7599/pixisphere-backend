package com.pixisphere.service;

import com.pixisphere.dto.request.InquiryRequest;
import com.pixisphere.dto.response.ApiResponse;
import com.pixisphere.dto.response.InquiryResponse;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface IInquiryService {

    ResponseEntity<ApiResponse<String>> createInquiry(InquiryRequest request, Principal principal);

    ResponseEntity<ApiResponse<List<InquiryResponse>>> getAssignedLeads(Principal principal);
}
