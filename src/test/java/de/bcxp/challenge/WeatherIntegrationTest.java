package de.bcxp.challenge;

import de.bcxp.challenge.evaluators.WeatherDataEvaluator;
import de.bcxp.challenge.models.WeatherData;
import de.bcxp.challenge.readers.CsvDataReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherIntegrationTest {
    @Test
    void evaluateWeatherIntegration() {
        //arrange
        CsvDataReader<WeatherData> reader = new CsvDataReader<>(WeatherData.class, ',');
        String filePath = "src/test/resources/de.bcxp.challenge/weather.csv";
        WeatherDataEvaluator evaluator = new WeatherDataEvaluator();

        // act
        int result;
        try {
            List<WeatherData> data = reader.readData(filePath);
            result = evaluator.evaluate(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // assert
        assertEquals(14, result);
    }
}
