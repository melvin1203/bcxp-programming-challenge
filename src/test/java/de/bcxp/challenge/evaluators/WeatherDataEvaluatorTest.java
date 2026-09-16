package de.bcxp.challenge.evaluators;

import de.bcxp.challenge.models.WeatherData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
