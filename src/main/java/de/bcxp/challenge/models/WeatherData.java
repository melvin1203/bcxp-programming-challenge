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
        @JsonProperty("Day") Integer day,
        @JsonProperty("MxT") Double maxTemp,
        @JsonProperty("MnT") Double minTemp
) {
    public WeatherData {
        if (day == null || maxTemp == null || minTemp == null) {
            throw new IllegalArgumentException("Missing required weather data fields (Day, MxT, or MnT).");
        }
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
