package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Paketler;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Paketler entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaketlerRepository extends JpaRepository<Paketler, Long> {
}
