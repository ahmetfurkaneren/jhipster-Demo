package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Fatura.
 */
@Entity
@Table(name = "fatura")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Fatura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ilk_odeme_tarihi", nullable = false)
    private Instant ilkOdemeTarihi;

    @NotNull
    @Column(name = "son_odeme_tarihi", nullable = false)
    private Instant sonOdemeTarihi;

    @Column(name = "odenen_tarih")
    private Instant odenenTarih;

    @NotNull
    @Column(name = "toplam_tutar", nullable = false)
    private Long toplamTutar;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "faturas", allowSetters = true)
    private Sozlesme sozlesme;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getIlkOdemeTarihi() {
        return ilkOdemeTarihi;
    }

    public Fatura ilkOdemeTarihi(Instant ilkOdemeTarihi) {
        this.ilkOdemeTarihi = ilkOdemeTarihi;
        return this;
    }

    public void setIlkOdemeTarihi(Instant ilkOdemeTarihi) {
        this.ilkOdemeTarihi = ilkOdemeTarihi;
    }

    public Instant getSonOdemeTarihi() {
        return sonOdemeTarihi;
    }

    public Fatura sonOdemeTarihi(Instant sonOdemeTarihi) {
        this.sonOdemeTarihi = sonOdemeTarihi;
        return this;
    }

    public void setSonOdemeTarihi(Instant sonOdemeTarihi) {
        this.sonOdemeTarihi = sonOdemeTarihi;
    }

    public Instant getOdenenTarih() {
        return odenenTarih;
    }

    public Fatura odenenTarih(Instant odenenTarih) {
        this.odenenTarih = odenenTarih;
        return this;
    }

    public void setOdenenTarih(Instant odenenTarih) {
        this.odenenTarih = odenenTarih;
    }

    public Long getToplamTutar() {
        return toplamTutar;
    }

    public Fatura toplamTutar(Long toplamTutar) {
        this.toplamTutar = toplamTutar;
        return this;
    }

    public void setToplamTutar(Long toplamTutar) {
        this.toplamTutar = toplamTutar;
    }

    public Sozlesme getSozlesme() {
        return sozlesme;
    }

    public Fatura sozlesme(Sozlesme sozlesme) {
        this.sozlesme = sozlesme;
        return this;
    }

    public void setSozlesme(Sozlesme sozlesme) {
        this.sozlesme = sozlesme;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fatura)) {
            return false;
        }
        return id != null && id.equals(((Fatura) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Fatura{" +
            "id=" + getId() +
            ", ilkOdemeTarihi='" + getIlkOdemeTarihi() + "'" +
            ", sonOdemeTarihi='" + getSonOdemeTarihi() + "'" +
            ", odenenTarih='" + getOdenenTarih() + "'" +
            ", toplamTutar=" + getToplamTutar() +
            "}";
    }
}
