package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.InternetKullanim;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InternetKullanim entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InternetKullanimRepository extends JpaRepository<InternetKullanim, Long> {
}
