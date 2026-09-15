package de.bcxp.challenge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds country data for a specific country.
 *
 * @param name       The name of the country.
 * @param population The population of the country.
 * @param area       The area of the country in square kilometers.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CountryData(
        @JsonProperty("Name") String name,
        @JsonProperty("Population") String population,
        @JsonProperty("Area (km²)") String area
) {
    public CountryData {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Country name cannot be null or empty");
        }
        if (population == null || population.isEmpty() || parseNumber(population) <= 0) {
            throw new IllegalArgumentException("Population cannot be null, empty and must be greater than 0");
        }
        if (area == null || area.isEmpty() || parseNumber(area) <= 0) {
            throw new IllegalArgumentException("Area cannot be null, empty and must be greater than 0");
        }
    }

    public double getPopulationDensity() {
        return parseNumber(population) / parseNumber(area);
    }

    private static double parseNumber(String number) {
        if (number == null || number.isEmpty()) {
            throw new IllegalArgumentException("Number string is null or empty");
        }
        String cleanNumber = number.replace(".", "").replace(",", ".").trim();
        try {
            return Double.parseDouble(cleanNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format: " + number, e);
        }
    }
}

