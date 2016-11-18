package de.kvb.mammasoft.evaluation.berichte.domain.med;

import java.util.List;

public interface ScreeningEinheitRepository {

    List<ScreeningEinheit> getScreeningEinheiten();

    ScreeningEinheit getScreeningEinheit(Long screeningEinheitId);

}
