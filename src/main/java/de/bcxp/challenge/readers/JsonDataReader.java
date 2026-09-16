package de.bcxp.challenge.readers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import de.bcxp.challenge.exceptions.EmptyDataException;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Reads data from a JSON file and maps them to domain objects of type T.
 */
public class JsonDataReader<T> implements DataReader<T> {
    private final ObjectMapper mapper;
    private final Class<T> type;

    public JsonDataReader(Class<T> type) {
        this.type = type;
        this.mapper = new ObjectMapper();
    }

    @Override
    public List<T> readData(String filePath) throws IOException {
        try {
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, type);
            List<T> result = mapper.readValue(new File(filePath), listType);

            if (result == null || result.isEmpty()) {
                throw new EmptyDataException("No data available in json file: " + filePath);
            }
            return result;
        } catch (IOException e) {
            throw new IOException("Error reading JSON file: " + e.getMessage(), e);
        }
    }
}
