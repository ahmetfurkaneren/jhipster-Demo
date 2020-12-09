package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TelNo.
 */
@Entity
@Table(name = "tel_no")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TelNo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "numara", nullable = false, unique = true)
    private Integer numara;

    @OneToMany(mappedBy = "telNo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Sozlesme> sozlesmes = new HashSet<>();

    @OneToMany(mappedBy = "telNo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DakikaKullanim> dakikaKullanims = new HashSet<>();

    @OneToMany(mappedBy = "telNo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<SmsKullanim> smsKullanims = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumara() {
        return numara;
    }

    public TelNo numara(Integer numara) {
        this.numara = numara;
        return this;
    }

    public void setNumara(Integer numara) {
        this.numara = numara;
    }

    public Set<Sozlesme> getSozlesmes() {
        return sozlesmes;
    }

    public TelNo sozlesmes(Set<Sozlesme> sozlesmes) {
        this.sozlesmes = sozlesmes;
        return this;
    }

    public TelNo addSozlesme(Sozlesme sozlesme) {
        this.sozlesmes.add(sozlesme);
        sozlesme.setTelNo(this);
        return this;
    }

    public TelNo removeSozlesme(Sozlesme sozlesme) {
        this.sozlesmes.remove(sozlesme);
        sozlesme.setTelNo(null);
        return this;
    }

    public void setSozlesmes(Set<Sozlesme> sozlesmes) {
        this.sozlesmes = sozlesmes;
    }

    public Set<DakikaKullanim> getDakikaKullanims() {
        return dakikaKullanims;
    }

    public TelNo dakikaKullanims(Set<DakikaKullanim> dakikaKullanims) {
        this.dakikaKullanims = dakikaKullanims;
        return this;
    }

    public TelNo addDakikaKullanim(DakikaKullanim dakikaKullanim) {
        this.dakikaKullanims.add(dakikaKullanim);
        dakikaKullanim.setTelNo(this);
        return this;
    }

    public TelNo removeDakikaKullanim(DakikaKullanim dakikaKullanim) {
        this.dakikaKullanims.remove(dakikaKullanim);
        dakikaKullanim.setTelNo(null);
        return this;
    }

    public void setDakikaKullanims(Set<DakikaKullanim> dakikaKullanims) {
        this.dakikaKullanims = dakikaKullanims;
    }

    public Set<SmsKullanim> getSmsKullanims() {
        return smsKullanims;
    }

    public TelNo smsKullanims(Set<SmsKullanim> smsKullanims) {
        this.smsKullanims = smsKullanims;
        return this;
    }

    public TelNo addSmsKullanim(SmsKullanim smsKullanim) {
        this.smsKullanims.add(smsKullanim);
        smsKullanim.setTelNo(this);
        return this;
    }

    public TelNo removeSmsKullanim(SmsKullanim smsKullanim) {
        this.smsKullanims.remove(smsKullanim);
        smsKullanim.setTelNo(null);
        return this;
    }

    public void setSmsKullanims(Set<SmsKullanim> smsKullanims) {
        this.smsKullanims = smsKullanims;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TelNo)) {
            return false;
        }
        return id != null && id.equals(((TelNo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TelNo{" +
            "id=" + getId() +
            ", numara=" + getNumara() +
            "}";
    }
}
