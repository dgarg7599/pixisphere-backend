package com.pixisphere.repository;

import com.pixisphere.entity.Partner;
import com.pixisphere.entity.PartnerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    Optional<Partner> findByUserId(Long userId);

    List<Partner> findByStatus(PartnerStatus status);

    long countByStatus(PartnerStatus status);

}
