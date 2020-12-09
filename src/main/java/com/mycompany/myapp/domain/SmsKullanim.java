package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A SmsKullanim.
 */
@Entity
@Table(name = "sms_kullanim")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SmsKullanim implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "tarih", nullable = false)
    private Instant tarih;

    @NotNull
    @Column(name = "icerik", nullable = false)
    private String icerik;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "smsKullanims", allowSetters = true)
    private SozlesmeninPaketleri sozlesmeninPaketleri;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "smsKullanims", allowSetters = true)
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

    public SmsKullanim tarih(Instant tarih) {
        this.tarih = tarih;
        return this;
    }

    public void setTarih(Instant tarih) {
        this.tarih = tarih;
    }

    public String getIcerik() {
        return icerik;
    }

    public SmsKullanim icerik(String icerik) {
        this.icerik = icerik;
        return this;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public SozlesmeninPaketleri getSozlesmeninPaketleri() {
        return sozlesmeninPaketleri;
    }

    public SmsKullanim sozlesmeninPaketleri(SozlesmeninPaketleri sozlesmeninPaketleri) {
        this.sozlesmeninPaketleri = sozlesmeninPaketleri;
        return this;
    }

    public void setSozlesmeninPaketleri(SozlesmeninPaketleri sozlesmeninPaketleri) {
        this.sozlesmeninPaketleri = sozlesmeninPaketleri;
    }

    public TelNo getTelNo() {
        return telNo;
    }

    public SmsKullanim telNo(TelNo telNo) {
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
        if (!(o instanceof SmsKullanim)) {
            return false;
        }
        return id != null && id.equals(((SmsKullanim) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SmsKullanim{" +
            "id=" + getId() +
            ", tarih='" + getTarih() + "'" +
            ", icerik='" + getIcerik() + "'" +
            "}";
    }
}
