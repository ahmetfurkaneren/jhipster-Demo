package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.SozlesmeninPaketleri;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SozlesmeninPaketleri entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SozlesmeninPaketleriRepository extends JpaRepository<SozlesmeninPaketleri, Long> {
}
