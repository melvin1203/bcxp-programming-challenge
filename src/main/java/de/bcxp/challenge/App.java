package de.bcxp.challenge;

import de.bcxp.challenge.evaluators.CountryDataEvaluator;
import de.bcxp.challenge.evaluators.DataEvaluator;
import de.bcxp.challenge.evaluators.WeatherDataEvaluator;
import de.bcxp.challenge.models.CountryData;
import de.bcxp.challenge.models.WeatherData;
import de.bcxp.challenge.readers.CsvDataReader;
import de.bcxp.challenge.readers.DataReader;
import de.bcxp.challenge.readers.JsonDataReader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

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
        } catch (IOException e) {
            log.error("Error reading data: {}", e.getMessage());
        }
    }

    private static String getDayWithSmallestTemperatureSpread() throws IOException {
        DataReader<WeatherData> weatherDataReader = new CsvDataReader<>(WeatherData.class, ',');
        List<WeatherData> weatherData = weatherDataReader.readData("src/main/resources/de/bcxp/challenge/weather.csv");

        DataEvaluator<WeatherData, Integer> weatherEvaluator = new WeatherDataEvaluator();
        return String.valueOf(weatherEvaluator.evaluate(weatherData));
    }

    private static String getCountryWithHighestPopulationDensity() throws IOException {
        DataReader<CountryData> countryDataReader = new CsvDataReader<>(CountryData.class, ';');
        List<CountryData> countryData = countryDataReader.readData("src/main/resources/de/bcxp/challenge/countries.csv");
        // DataReader<CountryData> countryDataReader = new JsonDataReader<>(CountryData.class);
        // List<CountryData> countryData = countryDataReader.readData("src/main/resources/de/bcxp/challenge/countries.json");

        DataEvaluator<CountryData, String> countryEvaluator = new CountryDataEvaluator();
        return countryEvaluator.evaluate(countryData);
    }
}
