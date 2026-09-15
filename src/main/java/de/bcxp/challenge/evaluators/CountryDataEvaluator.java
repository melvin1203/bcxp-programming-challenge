package de.bcxp.challenge.evaluators;


import de.bcxp.challenge.models.CountryData;

import java.util.Comparator;
import java.util.List;

/**
 * Evaluates countries data and returns the name of the country with the highest population density.
 */
public class CountryDataEvaluator implements DataEvaluator<CountryData, String> {

    @Override
    public String evaluate(List<CountryData> data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("no country data available for evaluation");
        }

        return data.stream()
                .max(Comparator.comparingDouble(CountryData::getPopulationDensity))
                .map(CountryData::name)
                .orElseThrow(() -> new IllegalArgumentException("No data available"));
    }
}
