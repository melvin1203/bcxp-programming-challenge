package de.bcxp.challenge.readers;

import de.bcxp.challenge.models.WeatherData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CsvDataReaderTest {

    @Test
    void readData_shouldReadWeatherDataFromCsvFile() throws IOException {
        // arrange
        CsvDataReader<WeatherData> reader = new CsvDataReader<>(WeatherData.class, ',');
        String filePath = "src/main/resources/de/bcxp/challenge/weather.csv";

        // act
        List<WeatherData> data = reader.readData(filePath);

        // assert
        assertNotNull(data);
        assertEquals(30, data.size());
        assertEquals(1, data.getFirst().day());
        assertEquals(88.0, data.getFirst().maxTemp());
        assertEquals(59.0, data.getFirst().minTemp());
    }

    @Test
    void readData_shouldReadCountryDataFromCsvFile() throws IOException {
        // arrange
        CsvDataReader<de.bcxp.challenge.models.CountryData> reader = new CsvDataReader<>(de.bcxp.challenge.models.CountryData.class, ';');
        String filePath = "src/main/resources/de/bcxp/challenge/countries.csv";

        // act
        List<de.bcxp.challenge.models.CountryData> data = reader.readData(filePath);

        // assert
        assertNotNull(data);
        assertEquals(27, data.size());
        assertEquals("Austria", data.get(0).name());
        assertEquals("8926000", data.get(0).population());
        assertEquals("83855", data.get(0).area());
    }
}
