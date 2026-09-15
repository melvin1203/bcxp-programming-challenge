package de.bcxp.challenge;

import de.bcxp.challenge.evaluators.DataEvaluator;
import de.bcxp.challenge.evaluators.WeatherDataEvaluator;
import de.bcxp.challenge.models.WeatherData;
import de.bcxp.challenge.readers.DataReader;
import de.bcxp.challenge.readers.JsonDataReader;

import java.io.IOException;
import java.util.List;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

    /**
     * This is the main entry method of your program.
     *
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {


        try {
            //DataReader<WeatherData> dataReader = new CsvDataReader();
            //List<WeatherData> weatherData = dataReader.readData("src/main/resources/de/bcxp/challenge/weather.csv");

            DataReader<WeatherData> dataReader = new JsonDataReader();
            List<WeatherData> weatherData = dataReader.readData("src/main/resources/de/bcxp/challenge/weather.json");

            DataEvaluator<WeatherData, Integer> weatherEvaluator = new WeatherDataEvaluator();
            String dayWithSmallestTempSpread = String.valueOf(weatherEvaluator.evaluate(weatherData));
            System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);
        } catch (IOException e) {
            System.err.println("Error reading data: " + e.getMessage());
        }

        String countryWithHighestPopulationDensity = "Some country"; // Your population density analysis function call …
        System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);
    }
}
