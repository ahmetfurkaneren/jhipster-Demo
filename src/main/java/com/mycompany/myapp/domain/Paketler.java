package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.mycompany.myapp.domain.enumeration.Aktif;

import com.mycompany.myapp.domain.enumeration.Tip;

import com.mycompany.myapp.domain.enumeration.PaketTipi;

import com.mycompany.myapp.domain.enumeration.Donem;

/**
 * A Paketler.
 */
@Entity
@Table(name = "paketler")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Paketler implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "paket_adi", nullable = false, unique = true)
    private String paketAdi;

    @Column(name = "aciklama")
    private String aciklama;

    @NotNull
    @Column(name = "baslangic_tarihi", nullable = false)
    private Instant baslangicTarihi;

    @NotNull
    @Column(name = "bitis_tarihi", nullable = false)
    private Instant bitisTarihi;

    @NotNull
    @Column(name = "fiyat", nullable = false)
    private Long fiyat;

    @NotNull
    @Column(name = "yeni_musteri_fiyat", nullable = false)
    private Long yeniMusteriFiyat;

    @Column(name = "tahahut_sure")
    private Integer tahahutSure;

    @Column(name = "dakika")
    private Integer dakika;

    @Column(name = "sms")
    private Integer sms;

    @Column(name = "internet")
    private Integer internet;

    @Enumerated(EnumType.STRING)
    @Column(name = "aktif")
    private Aktif aktif;

    @Enumerated(EnumType.STRING)
    @Column(name = "tip")
    private Tip tip;

    @Enumerated(EnumType.STRING)
    @Column(name = "paket_tipi")
    private PaketTipi paketTipi;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "donem", nullable = false)
    private Donem donem;

    @Column(name = "dakika_ucret")
    private Long dakikaUcret;

    @Column(name = "sms_ucret")
    private Long smsUcret;

    @Column(name = "internet_ucret")
    private Long internetUcret;

    @OneToMany(mappedBy = "paketler")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<SozlesmeninPaketleri> sozlesmeninPaketleris = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaketAdi() {
        return paketAdi;
    }

    public Paketler paketAdi(String paketAdi) {
        this.paketAdi = paketAdi;
        return this;
    }

    public void setPaketAdi(String paketAdi) {
        this.paketAdi = paketAdi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public Paketler aciklama(String aciklama) {
        this.aciklama = aciklama;
        return this;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public Instant getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public Paketler baslangicTarihi(Instant baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
        return this;
    }

    public void setBaslangicTarihi(Instant baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
    }

    public Instant getBitisTarihi() {
        return bitisTarihi;
    }

    public Paketler bitisTarihi(Instant bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
        return this;
    }

    public void setBitisTarihi(Instant bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
    }

    public Long getFiyat() {
        return fiyat;
    }

    public Paketler fiyat(Long fiyat) {
        this.fiyat = fiyat;
        return this;
    }

    public void setFiyat(Long fiyat) {
        this.fiyat = fiyat;
    }

    public Long getYeniMusteriFiyat() {
        return yeniMusteriFiyat;
    }

    public Paketler yeniMusteriFiyat(Long yeniMusteriFiyat) {
        this.yeniMusteriFiyat = yeniMusteriFiyat;
        return this;
    }

    public void setYeniMusteriFiyat(Long yeniMusteriFiyat) {
        this.yeniMusteriFiyat = yeniMusteriFiyat;
    }

    public Integer getTahahutSure() {
        return tahahutSure;
    }

    public Paketler tahahutSure(Integer tahahutSure) {
        this.tahahutSure = tahahutSure;
        return this;
    }

    public void setTahahutSure(Integer tahahutSure) {
        this.tahahutSure = tahahutSure;
    }

    public Integer getDakika() {
        return dakika;
    }

    public Paketler dakika(Integer dakika) {
        this.dakika = dakika;
        return this;
    }

    public void setDakika(Integer dakika) {
        this.dakika = dakika;
    }

    public Integer getSms() {
        return sms;
    }

    public Paketler sms(Integer sms) {
        this.sms = sms;
        return this;
    }

    public void setSms(Integer sms) {
        this.sms = sms;
    }

    public Integer getInternet() {
        return internet;
    }

    public Paketler internet(Integer internet) {
        this.internet = internet;
        return this;
    }

    public void setInternet(Integer internet) {
        this.internet = internet;
    }

    public Aktif getAktif() {
        return aktif;
    }

    public Paketler aktif(Aktif aktif) {
        this.aktif = aktif;
        return this;
    }

    public void setAktif(Aktif aktif) {
        this.aktif = aktif;
    }

    public Tip getTip() {
        return tip;
    }

    public Paketler tip(Tip tip) {
        this.tip = tip;
        return this;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public PaketTipi getPaketTipi() {
        return paketTipi;
    }

    public Paketler paketTipi(PaketTipi paketTipi) {
        this.paketTipi = paketTipi;
        return this;
    }

    public void setPaketTipi(PaketTipi paketTipi) {
        this.paketTipi = paketTipi;
    }

    public Donem getDonem() {
        return donem;
    }

    public Paketler donem(Donem donem) {
        this.donem = donem;
        return this;
    }

    public void setDonem(Donem donem) {
        this.donem = donem;
    }

    public Long getDakikaUcret() {
        return dakikaUcret;
    }

    public Paketler dakikaUcret(Long dakikaUcret) {
        this.dakikaUcret = dakikaUcret;
        return this;
    }

    public void setDakikaUcret(Long dakikaUcret) {
        this.dakikaUcret = dakikaUcret;
    }

    public Long getSmsUcret() {
        return smsUcret;
    }

    public Paketler smsUcret(Long smsUcret) {
        this.smsUcret = smsUcret;
        return this;
    }

    public void setSmsUcret(Long smsUcret) {
        this.smsUcret = smsUcret;
    }

    public Long getInternetUcret() {
        return internetUcret;
    }

    public Paketler internetUcret(Long internetUcret) {
        this.internetUcret = internetUcret;
        return this;
    }

    public void setInternetUcret(Long internetUcret) {
        this.internetUcret = internetUcret;
    }

    public Set<SozlesmeninPaketleri> getSozlesmeninPaketleris() {
        return sozlesmeninPaketleris;
    }

    public Paketler sozlesmeninPaketleris(Set<SozlesmeninPaketleri> sozlesmeninPaketleris) {
        this.sozlesmeninPaketleris = sozlesmeninPaketleris;
        return this;
    }

    public Paketler addSozlesmeninPaketleri(SozlesmeninPaketleri sozlesmeninPaketleri) {
        this.sozlesmeninPaketleris.add(sozlesmeninPaketleri);
        sozlesmeninPaketleri.setPaketler(this);
        return this;
    }

    public Paketler removeSozlesmeninPaketleri(SozlesmeninPaketleri sozlesmeninPaketleri) {
        this.sozlesmeninPaketleris.remove(sozlesmeninPaketleri);
        sozlesmeninPaketleri.setPaketler(null);
        return this;
    }

    public void setSozlesmeninPaketleris(Set<SozlesmeninPaketleri> sozlesmeninPaketleris) {
        this.sozlesmeninPaketleris = sozlesmeninPaketleris;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Paketler)) {
            return false;
        }
        return id != null && id.equals(((Paketler) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Paketler{" +
            "id=" + getId() +
            ", paketAdi='" + getPaketAdi() + "'" +
            ", aciklama='" + getAciklama() + "'" +
            ", baslangicTarihi='" + getBaslangicTarihi() + "'" +
            ", bitisTarihi='" + getBitisTarihi() + "'" +
            ", fiyat=" + getFiyat() +
            ", yeniMusteriFiyat=" + getYeniMusteriFiyat() +
            ", tahahutSure=" + getTahahutSure() +
            ", dakika=" + getDakika() +
            ", sms=" + getSms() +
            ", internet=" + getInternet() +
            ", aktif='" + getAktif() + "'" +
            ", tip='" + getTip() + "'" +
            ", paketTipi='" + getPaketTipi() + "'" +
            ", donem='" + getDonem() + "'" +
            ", dakikaUcret=" + getDakikaUcret() +
            ", smsUcret=" + getSmsUcret() +
            ", internetUcret=" + getInternetUcret() +
            "}";
    }
}
