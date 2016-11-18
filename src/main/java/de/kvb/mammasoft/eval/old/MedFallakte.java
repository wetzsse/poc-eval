package de.kvb.mammasoft.evaluation.berichte.domain.med;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Immutable;

import com.google.common.base.Function;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

@Entity
@Table(name = "FALLAKTE")
@Immutable
public class MedFallakte {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "UNTERSUCHUNGID")
    private Long usId;

    @Column(name = "SCREENINGEINHEITID")
    private Long screeningEinheitId;

    @Column(name = "KLIENTIN_SCREENING_ID")
    private String screeningId;

    @Column(name = "UNTERSUCHUNGSBEGINN")
    private Date untersuchungBegin;

    @OrderBy("erzeugtAm")
    // @OrderBy("id")
    @OneToMany(mappedBy = "fallakte")
    @Filter(name = "DOKU_EINGEREICHT_FILTER")
    private List<MedDoku> dokus;

    @Transient
    private Multimap<MedDokuTyp, MedDoku> dokusByType;

    public void initAfterLoad() {
        initialisiereDokus();
        initialisiereDokusByTypeMap();
    }

    private void initialisiereDokus() {
        for (MedDoku doku : dokus) {
            doku.init();
        }
    }

    private void initialisiereDokusByTypeMap() {
        dokusByType = Multimaps.index(dokus, new Function<MedDoku, MedDokuTyp>() {
            @Override
            public MedDokuTyp apply(MedDoku input) {
                return input.getDokuTyp();
            }

        });
    }

    public Collection<MedDoku> getNichtinvAbkl() {
        return getDokus(MedDokuTyp.nichtinvAbkl);
    }

    public Collection<MedDoku> getBiopsien() {
        return getDokus(MedDokuTyp.biopsie);
    }

    public Collection<MedDoku> getPathologieBiopsie() {
        return getDokus(MedDokuTyp.pathologieBiopsie);
    }

    public Collection<MedDoku> getDokus(MedDokuTyp dokuTyp) {
        return dokusByType.get(dokuTyp);
    }

    // public boolean hatDokus(MedDokuTyp dokuTyp) {
    // return dokusByType.containsKey(dokuTyp);
    // }

    // public Optional<MedDoku> getDoku(Predicate<MedDoku> dokuPredicate) {
    // return Iterables.tryFind(dokus, dokuPredicate);
    // }

    // public Collection<MedDoku> getDokus(Predicate<MedDoku> dokuPredicate) {
    // return Collections2.filter(dokus, dokuPredicate);
    // }

    // Getters

    public Long getId() {
        return id;
    }

    public Long getUsId() {
        return usId;
    }

    public Long getScreeningEinheitId() {
        return screeningEinheitId;
    }

    public String getScreeningId() {
        return screeningId;
    }

    public Date getUntersuchungBegin() {
        return untersuchungBegin;
    }

    public List<MedDoku> getDokus() {
        return dokus;
    }

}
