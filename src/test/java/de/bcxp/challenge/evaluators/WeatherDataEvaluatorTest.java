package de.bcxp.challenge.evaluators;

import de.bcxp.challenge.models.WeatherData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeatherDataEvaluatorTest {

    @Test
    void evaluate_shouldReturnDayWithSmallestTemperatureSpread() {
        // arrange
        WeatherDataEvaluator evaluator = new WeatherDataEvaluator();
        List<WeatherData> data = List.of(
                new WeatherData(1, 88.0, 59.0),
                new WeatherData(2, 79.0, 63.0),
                new WeatherData(3, 77.0, 55.0),
                new WeatherData(4, 77.0, 59.0),
                new WeatherData(5, 90.0, 66.0)
        );

        // act
        int result = evaluator.evaluate(data);

        // assert
        assertEquals(2, result);
    }

    @Test
    void evaluate_shouldFailForEmptyList() {
        WeatherDataEvaluator evaluator = new WeatherDataEvaluator();
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> evaluator.evaluate(List.of())
        );

        assertTrue(ex.getMessage().contains("No weather data available for evaluation"));
    }

    @Test
    void evaluate_shouldRejectInvalidDay() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new WeatherData(50, 88.0, 59.0)
        );

        assertTrue(ex.getMessage().contains("Day must be between 1 and 31"));
    }
}
