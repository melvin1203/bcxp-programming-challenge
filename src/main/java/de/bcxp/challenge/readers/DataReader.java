package de.bcxp.challenge.readers;

import java.io.IOException;
import java.util.List;
/**
 * Generic interface for reading data from a file.
 *
 * @param <T> The type of data to be read.
 */
public interface DataReader<T> {
    List<T> readData(String filePath) throws IOException;
}
