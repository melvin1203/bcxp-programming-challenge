package de.bcxp.challenge;

import de.bcxp.challenge.evaluators.CountryDataEvaluator;
import de.bcxp.challenge.models.CountryData;
import de.bcxp.challenge.readers.CsvDataReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryIntegrationTest {
    @Test
    void evaluateCountryIntegration() {
        //arrange
        CsvDataReader<CountryData> reader = new CsvDataReader<>(CountryData.class, ';');
        String filePath = "src/test/resources/de.bcxp.challenge/countries.csv";
        CountryDataEvaluator evaluator = new CountryDataEvaluator();

        // act
        String result;
        try {
            List<CountryData> data = reader.readData(filePath);
            result = evaluator.evaluate(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // assert
        assertEquals("Malta", result);
    }
}
