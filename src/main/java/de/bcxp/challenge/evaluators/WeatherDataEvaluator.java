package de.bcxp.challenge.evaluators;

import de.bcxp.challenge.models.WeatherData;

import java.util.Comparator;
import java.util.List;

/**
 * Evaluates weather data to find the day with the smallest temperature spread.
 */
public class WeatherDataEvaluator implements DataEvaluator<WeatherData, Integer> {
    @Override
    public Integer evaluate(List<WeatherData> data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("no weather data available for evaluation");
        }

        return data.stream()
                .min(Comparator.comparingDouble(WeatherData::getTempSpread))
                .map(WeatherData::day)
                .orElseThrow(() -> new IllegalArgumentException("No data available"));
    }
}
