package com.pixisphere.controller;

import com.pixisphere.dto.request.PortfolioRequest;
import com.pixisphere.dto.response.ApiResponse;
import com.pixisphere.dto.response.PortfolioResponse;
import com.pixisphere.entity.AppUser;
import com.pixisphere.service.IPortfolioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partner/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    private final IPortfolioService portfolioService;

    // Add a new portfolio entry
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addPortfolio(@AuthenticationPrincipal AppUser user,
                                                            @Valid @RequestBody PortfolioRequest request) {
        return portfolioService.addPortfolio(user.getId(), request);
    }

    // Get all portfolio entries of logged-in partner
    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<PortfolioResponse>>> getMyPortfolio(@AuthenticationPrincipal AppUser user) {
        return portfolioService.getMyPortfolio(user.getId());
    }

    // Update a portfolio entry by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<String>> updatePortfolio(@AuthenticationPrincipal AppUser user,
                                                               @PathVariable Long id,
                                                               @Valid @RequestBody PortfolioRequest request) {
        return portfolioService.updatePortfolio(user.getId(), id, request);
    }

    // Delete a portfolio entry by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deletePortfolio(@AuthenticationPrincipal AppUser user,
                                                               @PathVariable Long id) {
        return portfolioService.deletePortfolio(user.getId(), id);
    }
}
