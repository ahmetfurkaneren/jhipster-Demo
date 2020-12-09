package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DakikaKullanim.
 */
@Entity
@Table(name = "dakika_kullanim")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DakikaKullanim implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "tarih", nullable = false)
    private Instant tarih;

    @NotNull
    @Column(name = "miktar", nullable = false)
    private Integer miktar;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "dakikaKullanims", allowSetters = true)
    private SozlesmeninPaketleri sozlesmeninPaketleri;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "dakikaKullanims", allowSetters = true)
    private TelNo telNo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTarih() {
        return tarih;
    }

    public DakikaKullanim tarih(Instant tarih) {
        this.tarih = tarih;
        return this;
    }

    public void setTarih(Instant tarih) {
        this.tarih = tarih;
    }

    public Integer getMiktar() {
        return miktar;
    }

    public DakikaKullanim miktar(Integer miktar) {
        this.miktar = miktar;
        return this;
    }

    public void setMiktar(Integer miktar) {
        this.miktar = miktar;
    }

    public SozlesmeninPaketleri getSozlesmeninPaketleri() {
        return sozlesmeninPaketleri;
    }

    public DakikaKullanim sozlesmeninPaketleri(SozlesmeninPaketleri sozlesmeninPaketleri) {
        this.sozlesmeninPaketleri = sozlesmeninPaketleri;
        return this;
    }

    public void setSozlesmeninPaketleri(SozlesmeninPaketleri sozlesmeninPaketleri) {
        this.sozlesmeninPaketleri = sozlesmeninPaketleri;
    }

    public TelNo getTelNo() {
        return telNo;
    }

    public DakikaKullanim telNo(TelNo telNo) {
        this.telNo = telNo;
        return this;
    }

    public void setTelNo(TelNo telNo) {
        this.telNo = telNo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DakikaKullanim)) {
            return false;
        }
        return id != null && id.equals(((DakikaKullanim) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DakikaKullanim{" +
            "id=" + getId() +
            ", tarih='" + getTarih() + "'" +
            ", miktar=" + getMiktar() +
            "}";
    }
}
