package com.pixisphere.repository;

import com.pixisphere.entity.Partner;
import com.pixisphere.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findByPartner(Partner partner);
}
