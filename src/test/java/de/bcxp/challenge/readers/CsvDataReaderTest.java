package de.bcxp.challenge.readers;

import de.bcxp.challenge.exceptions.EmptyDataException;
import de.bcxp.challenge.models.WeatherData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvDataReaderTest {

    @Test
    void readData_shouldReadWeatherDataFromCsvFile() throws IOException {
        // arrange
        CsvDataReader<WeatherData> reader = new CsvDataReader<>(WeatherData.class, ',');
        String filePath = "src/test/resources/de.bcxp.challenge/weather.csv";

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
        String filePath = "src/test/resources/de.bcxp.challenge/countries.csv";

        // act
        List<de.bcxp.challenge.models.CountryData> data = reader.readData(filePath);

        // assert
        assertNotNull(data);
        assertEquals(27, data.size());
        assertEquals("Austria", data.getFirst().name());
        assertEquals("8926000", data.getFirst().population());
        assertEquals("83855", data.getFirst().area());
    }

    @Test
    void readData_shouldThrowIOExceptionForNonExistentFile() {
        // arrange
        CsvDataReader<WeatherData> reader = new CsvDataReader<>(WeatherData.class, ',');
        String filePath = "src/test/resources/de.bcxp.challenge/nonexistent.csv";

        // act & assert
        assertThrows(IOException.class, () -> reader.readData(filePath));
    }

    @Test
    void readData_onlyHeader_shouldThrowEmptyDataException() {
        // arrange
        CsvDataReader<WeatherData> reader = new CsvDataReader<>(WeatherData.class, ',');
        String filePath = "src/test/resources/de.bcxp.challenge/empty_weather.csv";

        // act & assert
        EmptyDataException exception = assertThrows(EmptyDataException.class, () -> reader.readData(filePath));
        assertTrue(exception.getMessage().contains("No data rows available for header in file: " + filePath));
    }
}
