package de.bcxp.challenge.readers;

import com.fasterxml.jackson.databind.MappingIterator;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * Reads data from a CSV file and maps them to domain objects of type T.
 */
public class CsvDataReader<T> implements DataReader<T> {
    private final Class<T> type;
    private final CsvMapper csvMapper;
    private final char delimiter;


    public CsvDataReader(Class<T> type, char delimiter) {
        this.type = type;
        this.csvMapper = new CsvMapper();
        this.delimiter = delimiter;
    }

    @Override
    public List<T> readData(String filePath) throws IOException {
        CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(delimiter);
        try {
            MappingIterator<T> iterator = csvMapper
                    .readerFor(type)
                    .with(schema)
                    .readValues(new File(filePath));
            return iterator.readAll();
        } catch (IOException e) {
            throw new IOException("Error reading CSV file: " + e.getMessage(), e);
        }
    }
}
