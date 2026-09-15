package de.bcxp.challenge.readers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.bcxp.challenge.models.WeatherData;

import java.io.IOException;
import java.util.List;

/**
 * This JsonDataReader reads weather data from a JSON file and converts it into a list of WeatherData objects.
 */
public class JsonDataReader implements DataReader<WeatherData> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<WeatherData> readData(String filePath) throws IOException {
        try {
            return List.of(mapper.readValue(new java.io.File(filePath), WeatherData[].class));
        } catch (IOException e) {
            throw new IOException("Error reading JSON file: " + e.getMessage(), e);
        }
    }
}
