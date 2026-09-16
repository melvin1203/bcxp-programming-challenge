package de.bcxp.challenge.evaluators;

import de.bcxp.challenge.models.CountryData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountryDataEvaluatorTest {

    @Test
    void evaluate_shouldReturnCountryWithHighestPopulationDensity() {
        // arrange
        CountryDataEvaluator evaluator = new CountryDataEvaluator();
        List<CountryData> data = List.of(
                new CountryData("CountryA", "1000000", "10000"),
                new CountryData("CountryB", "2000000", "20000"),
                new CountryData("CountryC", "3000000", "15000")
        );

        // act
        String result = evaluator.evaluate(data);

        // assert
        assertEquals("CountryC", result);
    }

    @Test
    void evaluate_shouldFailForEmptyList() {
        CountryDataEvaluator evaluator = new CountryDataEvaluator();
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> evaluator.evaluate(List.of())
        );

        assertTrue(ex.getMessage().contains("No country data available for evaluation"));
    }

    @Test
    void evaluate_shouldRejectInvalidArea() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new CountryData("CountryX", "1000", "0")
        );

        assertTrue(ex.getMessage().contains("Area cannot be null, empty and must be greater than 0"));
    }
}
