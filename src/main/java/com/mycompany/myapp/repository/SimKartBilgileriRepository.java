package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.SimKartBilgileri;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SimKartBilgileri entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SimKartBilgileriRepository extends JpaRepository<SimKartBilgileri, Long> {
}
