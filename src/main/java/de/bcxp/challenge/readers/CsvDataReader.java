package de.bcxp.challenge.readers;

import com.fasterxml.jackson.databind.MappingIterator;
import de.bcxp.challenge.models.WeatherData;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * This CsvDataReader reads weather data from a CSV file and converts it into a list of WeatherData objects.
 */
public class CsvDataReader implements DataReader<WeatherData> {
    private final CsvMapper csvMapper = new CsvMapper();


    @Override
    public List<WeatherData> readData(String filePath) throws IOException {
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        try {
            MappingIterator<WeatherData> iterator = csvMapper
                    .readerFor(WeatherData.class)
                    .with(schema)
                    .readValues(new File(filePath));
            return iterator.readAll();
        } catch (IOException e) {
            throw new IOException("Error reading CSV file: " + e.getMessage(), e);
        }
    }
}
