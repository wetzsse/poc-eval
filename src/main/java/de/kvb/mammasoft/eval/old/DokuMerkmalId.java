package de.kvb.mammasoft.evaluation.berichte.domain.med;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import static com.google.common.base.Preconditions.checkNotNull;

@Embeddable
public class DokuMerkmalId {

    @Column(name = "MERKMALNAME")
    private String name;

    @Embedded
    DokuMerkmalIndex merkmalIndex;

    protected DokuMerkmalId() {
        super();
    }

    public DokuMerkmalId(String name, DokuMerkmalIndex merkmalIndex) {
        this();
        this.name = checkNotNull(name);
        this.merkmalIndex = checkNotNull(merkmalIndex);

    }

    public DokuMerkmalIndex getMerkmalIndex() {
        return merkmalIndex;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "DokuMerkmalId [name=" + name + ", merkmalIndex=" + merkmalIndex + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, merkmalIndex);
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
        DokuMerkmalId other = (DokuMerkmalId) obj;
        return Objects.equals(this.name, other.name) && Objects.equals(this.merkmalIndex, other.merkmalIndex);
    }
}
