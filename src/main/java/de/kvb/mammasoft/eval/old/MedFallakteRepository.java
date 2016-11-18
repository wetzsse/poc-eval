package de.kvb.mammasoft.evaluation.berichte.domain.med;

import java.util.List;

import org.joda.time.Interval;

public interface MedFallakteRepository {

    MedFallakte findByUsid(Long usid);

    MedFallakte findById(Long id);

    List<MedFallakteMitPathologieBiopsieDoku> findMedFallakteMitPathologieBiopsieDokuBySeAndInterval(Long screeningEinheit, Interval interval);

}
