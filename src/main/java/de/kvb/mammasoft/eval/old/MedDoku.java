package de.kvb.mammasoft.evaluation.berichte.domain.med;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Immutable;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import static com.google.common.base.Preconditions.checkNotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Modeliert eine Dokumentation mit Messwerten aus MammaSoft-Med
 * <p>
 * Quelle: $HeadUrl$
 *
 * @version $Id$
 */
@Entity
@Table(name = "DOKU")
@Immutable
@FilterDef(name = "DOKU_EINGEREICHT_FILTER", defaultCondition = "status = 'EINGEREICHT'")
@Getter
@Setter
public class MedDoku implements Comparable<MedDoku> {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "DOKUTYPNAME")
    @Enumerated(EnumType.STRING)
    private MedDokuTyp dokuTyp;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private MedDokuStatus dokuStatus;

    @Column(name = "ERZEUGT_AM")
    private Date erzeugtAm;

    @Column(name = "DURCHGEFUEHRTVON")
    private String durchgefuehrtVon;

    @Column(name = "DURCHGEFUEHRTAM")
    private Date durchgefuehrtAm;

    @OneToMany
    @JoinColumn(name = "DOKU_ID")
    private List<DokuMerkmal> merkmale;

    @ManyToOne
    @JoinColumn(name = "FALLAKTE_ID")
    private MedFallakte fallakte;

    @Transient
    private Multimap<DokuMerkmalIndex, DokuMerkmal> merkmaleByIndex;

    //

    @Override
    public int compareTo(MedDoku otherMedDoku) {
        return ComparisonChain.start().compare(this.erzeugtAm, otherMedDoku.erzeugtAm).result();
    }

    @Override
    public String toString() {
        return "MedDoku [id=" + id + ", dokuTyp=" + dokuTyp + ", erzeugtAm=" + erzeugtAm + "]";
    }

    public boolean isEingereicht() {
        return dokuStatus == MedDokuStatus.EINGEREICHT;
    }

    public void init() {

        if (merkmale == null) {
            return;
        }

        merkmaleByIndex = Multimaps.index(merkmale, new Function<DokuMerkmal, DokuMerkmalIndex>() {
            @Override
            public DokuMerkmalIndex apply(DokuMerkmal input) {
                return Objects.firstNonNull(input.getDokuMerkmalId().getMerkmalIndex(), DokuMerkmalIndex.NULL_INDEX);
            }
        });
    }

    public Collection<DokuMerkmalIndex> getMerkmalInidzies() {
        return merkmaleByIndex != null ? merkmaleByIndex.keySet() : Collections.<DokuMerkmalIndex>emptyList();
    }

    public String getMerkmalWert(final String merkmalName) {
        return getMerkmalWert(merkmalName, DokuMerkmalIndex.NULL_INDEX);
    }

    public String getMerkmalWert(final String merkmalName, final DokuMerkmalIndex dokuMerkmalIndex) {
        checkNotNull(merkmalName);
        checkNotNull(dokuMerkmalIndex);

        if (merkmaleByIndex == null) {
            return null;
        }

        Collection<DokuMerkmal> collection = merkmaleByIndex.get(dokuMerkmalIndex);
        Optional<DokuMerkmal> optionalMerkmal = Iterables.tryFind(collection, new Predicate<DokuMerkmal>() {
            @Override
            public boolean apply(DokuMerkmal input) {
                return input.getDokuMerkmalId().getName().equalsIgnoreCase(merkmalName);
            }

        });

        if (optionalMerkmal.isPresent()) {
            return optionalMerkmal.get().getWert();
        } else {
            return null;
        }
    }

}
