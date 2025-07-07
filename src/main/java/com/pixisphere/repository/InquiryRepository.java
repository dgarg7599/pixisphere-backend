package com.pixisphere.repository;

import com.pixisphere.entity.AppUser;
import com.pixisphere.entity.Inquiry;
import com.pixisphere.entity.InquiryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    List<Inquiry> findByAssignedPartnersContaining(AppUser partner);

    List<Inquiry> findByClient(AppUser client);

    List<Inquiry> findByStatus(InquiryStatus status);
}
