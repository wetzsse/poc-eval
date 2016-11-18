package de.kvb.mammasoft.evaluation.berichte.domain.med.impl;

import java.util.regex.Pattern;

import com.google.common.base.Predicate;

import de.kvb.mammasoft.evaluation.berichte.domain.med.DokuMerkmalIndex;

public class LaesionIdIndexPredicate implements Predicate<DokuMerkmalIndex> {

    private static Pattern PATTERN = Pattern.compile("^(Rechts|Links)-[0-9]+$");

    @Override
    public boolean apply(DokuMerkmalIndex input) {
        if (input == null) {
            return false;
        }

        String indexValue = input.getIndex();

        if (indexValue == null) {
            return false;
        }

        return PATTERN.matcher(indexValue).matches();
    }
}
