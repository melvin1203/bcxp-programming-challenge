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
    public double getTempSpread() {
        return maxTemp - minTemp;
    }
}
