package de.bcxp.challenge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds weather data for a specific day.
 *
 * @param day     The day of the month.
 * @param maxTemp The maximum temperature recorded on that day.
 * @param minTemp The minimum temperature recorded on that day.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherData(
        @JsonProperty("Day") int day,
        @JsonProperty("MxT") double maxTemp,
        @JsonProperty("MnT") double minTemp
) {
    public WeatherData {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Day must be between 1 and 31");
        }
        if (maxTemp < minTemp) {
            throw new IllegalArgumentException("Max temperature cannot be less than min temperature");
        }
    }

    public double getTempSpread() {
        return maxTemp - minTemp;
    }
}
