package de.kvb.mammasoft.evaluation.berichte.domain.med;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "SCREENING_EINHEIT_S")
@Immutable
public class ScreeningEinheit implements Serializable {

    private static final long serialVersionUID = 209060079758921822L;

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "TEST_EINHEIT", nullable = false)
    Boolean testEinheit;

    @Column(name = "KENNUNG")
    private String kennung;

    // Hibernate
    protected ScreeningEinheit() {
        super();
    }

    public ScreeningEinheit(Long id, String name) {
        this(id, name, name);
    }

    public ScreeningEinheit(Long id, String name, String kennung) {
        this(id, name, kennung, Boolean.FALSE);
    }

    public ScreeningEinheit(Long id, String name, String kennung, Boolean testEinheit) {
        super();
        this.id = id;
        this.name = name;
        this.kennung = kennung;
        this.testEinheit = testEinheit;
    }

    public Long getId() {
        return id;
    }

    public String getKennung() {
        return kennung;
    }

    public String getName() {
        return name;
    }

    public boolean istTestEinheit() {
        return testEinheit == Boolean.TRUE;
    }
}
