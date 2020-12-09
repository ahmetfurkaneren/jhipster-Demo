package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.SirketBilgileri;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SirketBilgileri entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SirketBilgileriRepository extends JpaRepository<SirketBilgileri, Long> {
}
