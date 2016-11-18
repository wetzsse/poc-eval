package de.kvb.mammasoft.evaluation.berichte.domain.med.impl;

import com.google.common.base.Predicate;

import de.kvb.mammasoft.evaluation.berichte.domain.med.DokuMerkmalIndex;

public class UnbestimmteLaesionIdIndexPredicate implements Predicate<DokuMerkmalIndex> {

    @Override
    public boolean apply(DokuMerkmalIndex input) {
        if (input == null) {
            return false;
        }

        String indexValue = input.getIndex();

        if (indexValue == null) {
            return false;
        }
        return "Unbestimmt-0".equalsIgnoreCase(indexValue);
    }

}
