package com.pixisphere.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "inquiries")
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String city;
    private Double budget;

    private LocalDate eventDate;

    private String referenceImageUrl;

    @Enumerated(EnumType.STRING)
    private InquiryStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private AppUser client;

    @ManyToMany
    @JoinTable(
            name = "inquiry_assigned_partners",
            joinColumns = @JoinColumn(name = "inquiry_id"),
            inverseJoinColumns = @JoinColumn(name = "partner_id")
    )
    private List<AppUser> assignedPartners;
}
