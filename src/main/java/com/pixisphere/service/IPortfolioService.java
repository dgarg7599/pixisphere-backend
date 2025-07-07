package com.pixisphere.service;

import com.pixisphere.dto.request.PortfolioRequest;
import com.pixisphere.dto.response.ApiResponse;
import com.pixisphere.dto.response.PortfolioResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPortfolioService {
    ResponseEntity<ApiResponse<String>> addPortfolio(Long partnerId, PortfolioRequest request);
    ResponseEntity<ApiResponse<List<PortfolioResponse>>> getMyPortfolio(Long partnerId);
    ResponseEntity<ApiResponse<String>> updatePortfolio(Long partnerId, Long portfolioId, PortfolioRequest request);
    ResponseEntity<ApiResponse<String>> deletePortfolio(Long partnerId, Long portfolioId);
}
