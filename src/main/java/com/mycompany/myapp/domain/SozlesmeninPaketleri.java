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

/**
 * A SozlesmeninPaketleri.
 */
@Entity
@Table(name = "sozlesmenin_paketleri")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SozlesmeninPaketleri implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "fiyat", nullable = false)
    private Integer fiyat;

    @NotNull
    @Column(name = "baslangic_tarihi", nullable = false)
    private Instant baslangicTarihi;

    @NotNull
    @Column(name = "bitis_tarihi", nullable = false)
    private Instant bitisTarihi;

    @NotNull
    @Column(name = "kalan_dakika", nullable = false)
    private Integer kalanDakika;

    @NotNull
    @Column(name = "kalan_sms", nullable = false)
    private Integer kalanSms;

    @NotNull
    @Column(name = "kalan_internet", nullable = false)
    private Integer kalanInternet;

    @OneToMany(mappedBy = "sozlesmeninPaketleri")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DakikaKullanim> dakikaKullanims = new HashSet<>();

    @OneToMany(mappedBy = "sozlesmeninPaketleri")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<SmsKullanim> smsKullanims = new HashSet<>();

    @OneToMany(mappedBy = "sozlesmeninPaketleri")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<InternetKullanim> internetKullanims = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "sozlesmeninPaketleris", allowSetters = true)
    private Sozlesme sozlesme;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "sozlesmeninPaketleris", allowSetters = true)
    private Paketler paketler;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFiyat() {
        return fiyat;
    }

    public SozlesmeninPaketleri fiyat(Integer fiyat) {
        this.fiyat = fiyat;
        return this;
    }

    public void setFiyat(Integer fiyat) {
        this.fiyat = fiyat;
    }

    public Instant getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public SozlesmeninPaketleri baslangicTarihi(Instant baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
        return this;
    }

    public void setBaslangicTarihi(Instant baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
    }

    public Instant getBitisTarihi() {
        return bitisTarihi;
    }

    public SozlesmeninPaketleri bitisTarihi(Instant bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
        return this;
    }

    public void setBitisTarihi(Instant bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
    }

    public Integer getKalanDakika() {
        return kalanDakika;
    }

    public SozlesmeninPaketleri kalanDakika(Integer kalanDakika) {
        this.kalanDakika = kalanDakika;
        return this;
    }

    public void setKalanDakika(Integer kalanDakika) {
        this.kalanDakika = kalanDakika;
    }

    public Integer getKalanSms() {
        return kalanSms;
    }

    public SozlesmeninPaketleri kalanSms(Integer kalanSms) {
        this.kalanSms = kalanSms;
        return this;
    }

    public void setKalanSms(Integer kalanSms) {
        this.kalanSms = kalanSms;
    }

    public Integer getKalanInternet() {
        return kalanInternet;
    }

    public SozlesmeninPaketleri kalanInternet(Integer kalanInternet) {
        this.kalanInternet = kalanInternet;
        return this;
    }

    public void setKalanInternet(Integer kalanInternet) {
        this.kalanInternet = kalanInternet;
    }

    public Set<DakikaKullanim> getDakikaKullanims() {
        return dakikaKullanims;
    }

    public SozlesmeninPaketleri dakikaKullanims(Set<DakikaKullanim> dakikaKullanims) {
        this.dakikaKullanims = dakikaKullanims;
        return this;
    }

    public SozlesmeninPaketleri addDakikaKullanim(DakikaKullanim dakikaKullanim) {
        this.dakikaKullanims.add(dakikaKullanim);
        dakikaKullanim.setSozlesmeninPaketleri(this);
        return this;
    }

    public SozlesmeninPaketleri removeDakikaKullanim(DakikaKullanim dakikaKullanim) {
        this.dakikaKullanims.remove(dakikaKullanim);
        dakikaKullanim.setSozlesmeninPaketleri(null);
        return this;
    }

    public void setDakikaKullanims(Set<DakikaKullanim> dakikaKullanims) {
        this.dakikaKullanims = dakikaKullanims;
    }

    public Set<SmsKullanim> getSmsKullanims() {
        return smsKullanims;
    }

    public SozlesmeninPaketleri smsKullanims(Set<SmsKullanim> smsKullanims) {
        this.smsKullanims = smsKullanims;
        return this;
    }

    public SozlesmeninPaketleri addSmsKullanim(SmsKullanim smsKullanim) {
        this.smsKullanims.add(smsKullanim);
        smsKullanim.setSozlesmeninPaketleri(this);
        return this;
    }

    public SozlesmeninPaketleri removeSmsKullanim(SmsKullanim smsKullanim) {
        this.smsKullanims.remove(smsKullanim);
        smsKullanim.setSozlesmeninPaketleri(null);
        return this;
    }

    public void setSmsKullanims(Set<SmsKullanim> smsKullanims) {
        this.smsKullanims = smsKullanims;
    }

    public Set<InternetKullanim> getInternetKullanims() {
        return internetKullanims;
    }

    public SozlesmeninPaketleri internetKullanims(Set<InternetKullanim> internetKullanims) {
        this.internetKullanims = internetKullanims;
        return this;
    }

    public SozlesmeninPaketleri addInternetKullanim(InternetKullanim internetKullanim) {
        this.internetKullanims.add(internetKullanim);
        internetKullanim.setSozlesmeninPaketleri(this);
        return this;
    }

    public SozlesmeninPaketleri removeInternetKullanim(InternetKullanim internetKullanim) {
        this.internetKullanims.remove(internetKullanim);
        internetKullanim.setSozlesmeninPaketleri(null);
        return this;
    }

    public void setInternetKullanims(Set<InternetKullanim> internetKullanims) {
        this.internetKullanims = internetKullanims;
    }

    public Sozlesme getSozlesme() {
        return sozlesme;
    }

    public SozlesmeninPaketleri sozlesme(Sozlesme sozlesme) {
        this.sozlesme = sozlesme;
        return this;
    }

    public void setSozlesme(Sozlesme sozlesme) {
        this.sozlesme = sozlesme;
    }

    public Paketler getPaketler() {
        return paketler;
    }

    public SozlesmeninPaketleri paketler(Paketler paketler) {
        this.paketler = paketler;
        return this;
    }

    public void setPaketler(Paketler paketler) {
        this.paketler = paketler;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SozlesmeninPaketleri)) {
            return false;
        }
        return id != null && id.equals(((SozlesmeninPaketleri) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SozlesmeninPaketleri{" +
            "id=" + getId() +
            ", fiyat=" + getFiyat() +
            ", baslangicTarihi='" + getBaslangicTarihi() + "'" +
            ", bitisTarihi='" + getBitisTarihi() + "'" +
            ", kalanDakika=" + getKalanDakika() +
            ", kalanSms=" + getKalanSms() +
            ", kalanInternet=" + getKalanInternet() +
            "}";
    }
}
