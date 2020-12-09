package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.mycompany.myapp.domain.enumeration.MusteriTipi;

/**
 * A Musteri.
 */
@Entity
@Table(name = "musteri")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Musteri implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ad", nullable = false)
    private String ad;

    @NotNull
    @Column(name = "soyad", nullable = false)
    private String soyad;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "parola", nullable = false)
    private String parola;

    @NotNull
    @Size(min = 11, max = 11)
    @Column(name = "t_c", length = 11, nullable = false)
    private String tC;

    @Enumerated(EnumType.STRING)
    @Column(name = "musteri_tipi")
    private MusteriTipi musteriTipi;

    @NotNull
    @Column(name = "dogum_tarihi", nullable = false)
    private Instant dogumTarihi;

    @OneToMany(mappedBy = "musteri")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Sozlesme> sozlesmes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public Musteri ad(String ad) {
        this.ad = ad;
        return this;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public Musteri soyad(String soyad) {
        this.soyad = soyad;
        return this;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getEmail() {
        return email;
    }

    public Musteri email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public Musteri parola(String parola) {
        this.parola = parola;
        return this;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String gettC() {
        return tC;
    }

    public Musteri tC(String tC) {
        this.tC = tC;
        return this;
    }

    public void settC(String tC) {
        this.tC = tC;
    }

    public MusteriTipi getMusteriTipi() {
        return musteriTipi;
    }

    public Musteri musteriTipi(MusteriTipi musteriTipi) {
        this.musteriTipi = musteriTipi;
        return this;
    }

    public void setMusteriTipi(MusteriTipi musteriTipi) {
        this.musteriTipi = musteriTipi;
    }

    public Instant getDogumTarihi() {
        return dogumTarihi;
    }

    public Musteri dogumTarihi(Instant dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
        return this;
    }

    public void setDogumTarihi(Instant dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public Set<Sozlesme> getSozlesmes() {
        return sozlesmes;
    }

    public Musteri sozlesmes(Set<Sozlesme> sozlesmes) {
        this.sozlesmes = sozlesmes;
        return this;
    }

    public Musteri addSozlesme(Sozlesme sozlesme) {
        this.sozlesmes.add(sozlesme);
        sozlesme.setMusteri(this);
        return this;
    }

    public Musteri removeSozlesme(Sozlesme sozlesme) {
        this.sozlesmes.remove(sozlesme);
        sozlesme.setMusteri(null);
        return this;
    }

    public void setSozlesmes(Set<Sozlesme> sozlesmes) {
        this.sozlesmes = sozlesmes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Musteri)) {
            return false;
        }
        return id != null && id.equals(((Musteri) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Musteri{" +
            "id=" + getId() +
            ", ad='" + getAd() + "'" +
            ", soyad='" + getSoyad() + "'" +
            ", email='" + getEmail() + "'" +
            ", parola='" + getParola() + "'" +
            ", tC='" + gettC() + "'" +
            ", musteriTipi='" + getMusteriTipi() + "'" +
            ", dogumTarihi='" + getDogumTarihi() + "'" +
            "}";
    }
}
