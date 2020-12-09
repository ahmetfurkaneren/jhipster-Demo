package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TelNo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TelNo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TelNoRepository extends JpaRepository<TelNo, Long> {
}
