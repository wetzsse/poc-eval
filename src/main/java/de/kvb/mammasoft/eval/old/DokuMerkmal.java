package de.kvb.mammasoft.evaluation.berichte.domain.med;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "MESSWERT")
@Immutable
public class DokuMerkmal {

    @Id
    @Column(name = "ID")
    private Long id;

    @Embedded
    private DokuMerkmalId dokuMerkmalId;

    @Column(name = "WERT")
    private String wert;

    public Long getId() {
        return id;
    }

    public DokuMerkmalId getDokuMerkmalId() {
        return dokuMerkmalId;
    }

    public String getWert() {
        return wert;
    }

    @Override
    public String toString() {
        return "DokuMerkmal [id=" + id + ", dokuMerkmalId=" + dokuMerkmalId + ", wert=" + wert + "]";
    }

}
