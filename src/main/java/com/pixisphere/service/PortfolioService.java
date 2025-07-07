package com.pixisphere.service;

import com.pixisphere.dto.request.PortfolioRequest;
import com.pixisphere.dto.response.ApiResponse;
import com.pixisphere.dto.response.PortfolioResponse;
import com.pixisphere.entity.Partner;
import com.pixisphere.entity.Portfolio;
import com.pixisphere.repository.PartnerRepository;
import com.pixisphere.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PortfolioService implements IPortfolioService {

    private final PartnerRepository partnerRepository;
    private final PortfolioRepository portfolioRepository;

    @Override
    public ResponseEntity<ApiResponse<String>> addPortfolio(Long partnerId, PortfolioRequest request) {
        Partner partner = partnerRepository.findByUserId(partnerId)
                .orElseThrow(() -> new RuntimeException("Partner not found"));

        Portfolio portfolio = Portfolio.builder()
                .imageUrl(request.getImageUrl())
                .description(request.getDescription())
                .displayOrder(request.getDisplayOrder())
                .partner(partner)
                .build();

        portfolioRepository.save(portfolio);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .message("Portfolio image added")
                        .data("success")
                        .build()
        );
    }

    @Override
    public ResponseEntity<ApiResponse<List<PortfolioResponse>>> getMyPortfolio(Long partnerId) {
        Partner partner = partnerRepository.findByUserId(partnerId)
                .orElseThrow(() -> new RuntimeException("Partner not found"));

        List<Portfolio> portfolios = portfolioRepository.findByPartner(partner);

        List<PortfolioResponse> response = portfolios.stream()
                .map(p -> PortfolioResponse.builder()
                        .id(p.getId())
                        .imageUrl(p.getImageUrl())
                        .description(p.getDescription())
                        .displayOrder(p.getDisplayOrder())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                ApiResponse.<List<PortfolioResponse>>builder()
                        .message("Partner Portfolio")
                        .data(response)
                        .build()
        );
    }

    @Override
    public ResponseEntity<ApiResponse<String>> updatePortfolio(Long partnerId, Long portfolioId, PortfolioRequest request) {
        Partner partner = partnerRepository.findByUserId(partnerId)
                .orElseThrow(() -> new RuntimeException("Partner not found"));

        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new RuntimeException("Portfolio not found"));

        if (!portfolio.getPartner().getId().equals(partner.getId())) {
            return ResponseEntity.status(403).body(ApiResponse.<String>builder()
                    .message("You are not authorized to update this portfolio")
                    .data(null)
                    .build());
        }

        portfolio.setImageUrl(request.getImageUrl());
        portfolio.setDescription(request.getDescription());
        portfolio.setDisplayOrder(request.getDisplayOrder());

        portfolioRepository.save(portfolio);

        return ResponseEntity.ok(ApiResponse.<String>builder()
                .message("Portfolio updated successfully")
                .data("success")
                .build());
    }

    @Override
    public ResponseEntity<ApiResponse<String>> deletePortfolio(Long partnerId, Long portfolioId) {
        Partner partner = partnerRepository.findByUserId(partnerId)
                .orElseThrow(() -> new RuntimeException("Partner not found"));

        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new RuntimeException("Portfolio not found"));

        if (!portfolio.getPartner().getId().equals(partner.getId())) {
            return ResponseEntity.status(403).body(ApiResponse.<String>builder()
                    .message("You are not authorized to delete this portfolio")
                    .data(null)
                    .build());
        }

        portfolioRepository.delete(portfolio);

        return ResponseEntity.ok(ApiResponse.<String>builder()
                .message("Portfolio deleted successfully")
                .data("success")
                .build());
    }
}
