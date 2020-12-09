package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DakikaKullanim;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DakikaKullanim entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DakikaKullanimRepository extends JpaRepository<DakikaKullanim, Long> {
}
