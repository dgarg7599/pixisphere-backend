package com.pixisphere.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "partners")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String serviceCategory; // e.g., wedding, maternity

    private String city;

    private String aadharNumber;

    @ElementCollection
    @CollectionTable(name = "partner_portfolio", joinColumns = @JoinColumn(name = "partner_id"))
    @Column(name = "portfolio_url")
    private List<String> samplePortfolioUrls;

    @Enumerated(EnumType.STRING)
    private PartnerStatus status = PartnerStatus.PENDING;

    private String verificationComment;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;
}
