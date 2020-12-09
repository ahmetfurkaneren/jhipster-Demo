package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A SimKartBilgileri.
 */
@Entity
@Table(name = "sim_kart_bilgileri")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SimKartBilgileri implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Max(value = 4)
    @Column(name = "pin_no", nullable = false)
    private Integer pinNo;

    @NotNull
    @Max(value = 8)
    @Column(name = "puk_no", nullable = false)
    private Integer pukNo;

    @NotNull
    @Column(name = "barkod", nullable = false)
    private String barkod;

    @NotNull
    @Column(name = "bit_miktari", nullable = false)
    private Integer bitMiktari;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Sozlesme sozlesme;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPinNo() {
        return pinNo;
    }

    public SimKartBilgileri pinNo(Integer pinNo) {
        this.pinNo = pinNo;
        return this;
    }

    public void setPinNo(Integer pinNo) {
        this.pinNo = pinNo;
    }

    public Integer getPukNo() {
        return pukNo;
    }

    public SimKartBilgileri pukNo(Integer pukNo) {
        this.pukNo = pukNo;
        return this;
    }

    public void setPukNo(Integer pukNo) {
        this.pukNo = pukNo;
    }

    public String getBarkod() {
        return barkod;
    }

    public SimKartBilgileri barkod(String barkod) {
        this.barkod = barkod;
        return this;
    }

    public void setBarkod(String barkod) {
        this.barkod = barkod;
    }

    public Integer getBitMiktari() {
        return bitMiktari;
    }

    public SimKartBilgileri bitMiktari(Integer bitMiktari) {
        this.bitMiktari = bitMiktari;
        return this;
    }

    public void setBitMiktari(Integer bitMiktari) {
        this.bitMiktari = bitMiktari;
    }

    public Sozlesme getSozlesme() {
        return sozlesme;
    }

    public SimKartBilgileri sozlesme(Sozlesme sozlesme) {
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
        if (!(o instanceof SimKartBilgileri)) {
            return false;
        }
        return id != null && id.equals(((SimKartBilgileri) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SimKartBilgileri{" +
            "id=" + getId() +
            ", pinNo=" + getPinNo() +
            ", pukNo=" + getPukNo() +
            ", barkod='" + getBarkod() + "'" +
            ", bitMiktari=" + getBitMiktari() +
            "}";
    }
}
