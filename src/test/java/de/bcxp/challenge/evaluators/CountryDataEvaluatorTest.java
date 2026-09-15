package de.bcxp.challenge.evaluators;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryDataEvaluatorTest {

    @Test
    void evaluate_shouldReturnCountryWithHighestPopulationDensity() {
        // arrange
        CountryDataEvaluator evaluator = new CountryDataEvaluator();
        var data = List.of(
                new de.bcxp.challenge.models.CountryData("CountryA", "1000000", "10000"),
                new de.bcxp.challenge.models.CountryData("CountryB", "2000000", "20000"),
                new de.bcxp.challenge.models.CountryData("CountryC", "3000000", "15000")
        );

        // act
        String result = evaluator.evaluate(data);

        // assert
        assertEquals("CountryC", result);
    }
}
