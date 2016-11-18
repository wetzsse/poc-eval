package de.kvb.mammasoft.evaluation.berichte.domain.med;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DokuMerkmalIndex {

    public final static DokuMerkmalIndex NULL_INDEX = new DokuMerkmalIndex();

    @Column(name = "MESSWERTINDEX")
    private String index;

    // Hibernate
    protected DokuMerkmalIndex() {
        super();
    }

    public DokuMerkmalIndex(String index) {
        super();
        this.index = index;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "DokuMerkmalIndex [index=" + index + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DokuMerkmalIndex other = (DokuMerkmalIndex) obj;
        return Objects.equals(this.index, other.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }

}
