package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.mycompany.myapp.domain.enumeration.Tip;

/**
 * A Sozlesme.
 */
@Entity
@Table(name = "sozlesme")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Sozlesme implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tip")
    private Tip tip;

    @NotNull
    @Column(name = "tarih", nullable = false)
    private Instant tarih;

    @Column(name = "bitis_tarihi")
    private Instant bitisTarihi;

    @OneToMany(mappedBy = "sozlesme")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<SozlesmeninPaketleri> sozlesmeninPaketleris = new HashSet<>();

    @OneToMany(mappedBy = "sozlesme")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Fatura> faturas = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "sozlesmes", allowSetters = true)
    private Musteri musteri;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "sozlesmes", allowSetters = true)
    private TelNo telNo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tip getTip() {
        return tip;
    }

    public Sozlesme tip(Tip tip) {
        this.tip = tip;
        return this;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public Instant getTarih() {
        return tarih;
    }

    public Sozlesme tarih(Instant tarih) {
        this.tarih = tarih;
        return this;
    }

    public void setTarih(Instant tarih) {
        this.tarih = tarih;
    }

    public Instant getBitisTarihi() {
        return bitisTarihi;
    }

    public Sozlesme bitisTarihi(Instant bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
        return this;
    }

    public void setBitisTarihi(Instant bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
    }

    public Set<SozlesmeninPaketleri> getSozlesmeninPaketleris() {
        return sozlesmeninPaketleris;
    }

    public Sozlesme sozlesmeninPaketleris(Set<SozlesmeninPaketleri> sozlesmeninPaketleris) {
        this.sozlesmeninPaketleris = sozlesmeninPaketleris;
        return this;
    }

    public Sozlesme addSozlesmeninPaketleri(SozlesmeninPaketleri sozlesmeninPaketleri) {
        this.sozlesmeninPaketleris.add(sozlesmeninPaketleri);
        sozlesmeninPaketleri.setSozlesme(this);
        return this;
    }

    public Sozlesme removeSozlesmeninPaketleri(SozlesmeninPaketleri sozlesmeninPaketleri) {
        this.sozlesmeninPaketleris.remove(sozlesmeninPaketleri);
        sozlesmeninPaketleri.setSozlesme(null);
        return this;
    }

    public void setSozlesmeninPaketleris(Set<SozlesmeninPaketleri> sozlesmeninPaketleris) {
        this.sozlesmeninPaketleris = sozlesmeninPaketleris;
    }

    public Set<Fatura> getFaturas() {
        return faturas;
    }

    public Sozlesme faturas(Set<Fatura> faturas) {
        this.faturas = faturas;
        return this;
    }

    public Sozlesme addFatura(Fatura fatura) {
        this.faturas.add(fatura);
        fatura.setSozlesme(this);
        return this;
    }

    public Sozlesme removeFatura(Fatura fatura) {
        this.faturas.remove(fatura);
        fatura.setSozlesme(null);
        return this;
    }

    public void setFaturas(Set<Fatura> faturas) {
        this.faturas = faturas;
    }

    public Musteri getMusteri() {
        return musteri;
    }

    public Sozlesme musteri(Musteri musteri) {
        this.musteri = musteri;
        return this;
    }

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
    }

    public TelNo getTelNo() {
        return telNo;
    }

    public Sozlesme telNo(TelNo telNo) {
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
        if (!(o instanceof Sozlesme)) {
            return false;
        }
        return id != null && id.equals(((Sozlesme) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Sozlesme{" +
            "id=" + getId() +
            ", tip='" + getTip() + "'" +
            ", tarih='" + getTarih() + "'" +
            ", bitisTarihi='" + getBitisTarihi() + "'" +
            "}";
    }
}
