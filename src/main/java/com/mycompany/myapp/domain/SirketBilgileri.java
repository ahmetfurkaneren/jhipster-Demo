package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A SirketBilgileri.
 */
@Entity
@Table(name = "sirket_bilgileri")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SirketBilgileri implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "sirket_adi", nullable = false)
    private String sirketAdi;

    @NotNull
    @Column(name = "sirket_adresi", nullable = false)
    private String sirketAdresi;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Musteri musteri;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSirketAdi() {
        return sirketAdi;
    }

    public SirketBilgileri sirketAdi(String sirketAdi) {
        this.sirketAdi = sirketAdi;
        return this;
    }

    public void setSirketAdi(String sirketAdi) {
        this.sirketAdi = sirketAdi;
    }

    public String getSirketAdresi() {
        return sirketAdresi;
    }

    public SirketBilgileri sirketAdresi(String sirketAdresi) {
        this.sirketAdresi = sirketAdresi;
        return this;
    }

    public void setSirketAdresi(String sirketAdresi) {
        this.sirketAdresi = sirketAdresi;
    }

    public Musteri getMusteri() {
        return musteri;
    }

    public SirketBilgileri musteri(Musteri musteri) {
        this.musteri = musteri;
        return this;
    }

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SirketBilgileri)) {
            return false;
        }
        return id != null && id.equals(((SirketBilgileri) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SirketBilgileri{" +
            "id=" + getId() +
            ", sirketAdi='" + getSirketAdi() + "'" +
            ", sirketAdresi='" + getSirketAdresi() + "'" +
            "}";
    }
}
