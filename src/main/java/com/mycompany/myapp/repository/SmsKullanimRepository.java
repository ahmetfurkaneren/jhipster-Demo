package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.SmsKullanim;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SmsKullanim entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SmsKullanimRepository extends JpaRepository<SmsKullanim, Long> {
}
