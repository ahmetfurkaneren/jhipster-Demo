package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Sozlesme;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Sozlesme entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SozlesmeRepository extends JpaRepository<Sozlesme, Long> {
}
