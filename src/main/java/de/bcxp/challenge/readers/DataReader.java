package de.bcxp.challenge.readers;

import java.io.IOException;
import java.util.List;
/**
 * Generic interface for reading data from a file.
 *
 * @param <T> The type of data to be read.
 */
public interface DataReader<T> {
    /**
     * Reads data from the specified file and maps it to a list of domain objects of type T.
     *
     * @param filePath The path to the file to read.
     * @return A list of domain objects of type T.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    List<T> readData(String filePath) throws IOException;
}
