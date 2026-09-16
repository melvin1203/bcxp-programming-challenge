package de.bcxp.challenge;

import de.bcxp.challenge.evaluators.CountryDataEvaluator;
import de.bcxp.challenge.evaluators.DataEvaluator;
import de.bcxp.challenge.evaluators.WeatherDataEvaluator;
import de.bcxp.challenge.models.CountryData;
import de.bcxp.challenge.models.WeatherData;
import de.bcxp.challenge.readers.CsvDataReader;
import de.bcxp.challenge.readers.DataReader;

import java.io.IOException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;


/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
@Slf4j
public final class App {
    /**
     * This is the main entry method of your program.
     *
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        try {
            // Weather data analysis
            String dayWithSmallestTempSpread = getDayWithSmallestTemperatureSpread();
            log.info("Day with smallest temperature spread: {}", dayWithSmallestTempSpread);

            // Country data analysis
            String countryWithHighestPopulationDensity = getCountryWithHighestPopulationDensity();
            log.info("Country with highest population density: {}", countryWithHighestPopulationDensity);
        } catch (IOException | IllegalArgumentException e) {
            log.error("Error analyzing data: {}", e.getMessage());
        }
    }

    private static String getDayWithSmallestTemperatureSpread() throws IOException {
        DataReader<WeatherData> weatherDataReader = new CsvDataReader<>(WeatherData.class, ',');
        List<WeatherData> weatherData = weatherDataReader.readData("src/main/resources/de/bcxp/challenge/weather.csv");
        log.debug("Weather data with {} records successfully read: {}", weatherData.size(), weatherData);

        DataEvaluator<WeatherData, Integer> weatherEvaluator = new WeatherDataEvaluator();
        return String.valueOf(weatherEvaluator.evaluate(weatherData));
    }

    private static String getCountryWithHighestPopulationDensity() throws IOException {
        DataReader<CountryData> countryDataReader = new CsvDataReader<>(CountryData.class, ';');
        List<CountryData> countryData = countryDataReader.readData("src/main/resources/de/bcxp/challenge/countries.csv");
        log.debug("Country data with {} records successfully read: {}", countryData.size(), countryData);

        DataEvaluator<CountryData, String> countryEvaluator = new CountryDataEvaluator();
        return countryEvaluator.evaluate(countryData);
    }
}
