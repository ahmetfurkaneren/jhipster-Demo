package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Musteri;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Musteri entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MusteriRepository extends JpaRepository<Musteri, Long> {
}
