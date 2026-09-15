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
                new WeatherData(1, 88, 59),
                new WeatherData(2, 79, 63),
                new WeatherData(3, 77, 55),
                new WeatherData(4, 77, 59),
                new WeatherData(5, 90, 66)
        );

        // act
        int result = evaluator.evaluate(data);

        // assert
        assertEquals(2, result);
    }
}
