package de.kvb.mammasoft.evaluation.berichte.domain.med;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by wetzsse on 15.11.2016.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MedFallakteMitPathologieBiopsieDoku {

    @NonNull
    private String pathologeId;

    @NonNull
    private Long fallakteId;

}
