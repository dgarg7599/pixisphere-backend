package com.pixisphere.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminStatsResponse {
    private long totalClients;
    private long totalPartners;
    private long pendingVerifications;
    private long totalInquiries;
}
