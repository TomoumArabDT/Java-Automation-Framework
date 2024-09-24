package Data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvHandler {

    /**
     * Reads a CSV file and returns the data as a List of Maps, where each Map represents a row.
     *
     * @param fileName the name of the CSV file to read
     * @return a List of Maps, with each Map representing a row in the CSV file
     * @throws IOException if an I/O error occurs
     */
    public List<Map<String, String>> readCsvFromFile(String fileName) throws IOException {
        List<Map<String, String>> csvData = new ArrayList<>();
        try (FileReader reader = new FileReader(fileName);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord csvRecord : csvParser) {
                Map<String, String> rowMap = new HashMap<>();
                csvRecord.toMap().forEach(rowMap::put);
                csvData.add(rowMap);
            }
        }
        return csvData;
    }

    /**
     * Writes a List of Maps to a CSV file, where each Map represents a row.
     *
     * @param fileName the name of the CSV file to write to
     * @param data     the List of Maps representing the CSV data
     * @throws IOException if an I/O error occurs
     */
    public void writeCsvToFile(String fileName, List<Map<String, String>> data) throws IOException {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Data is empty, cannot write to CSV.");
        }

        try (FileWriter writer = new FileWriter(new File(fileName));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(data.get(0).keySet().toArray(new String[0])))) {

            for (Map<String, String> rowData : data) {
                csvPrinter.printRecord(rowData.values());
            }
        }
    }

    /**
     * Reads a specific value from a CSV file based on the provided column name and row index.
     *
     * @param fileName the name of the CSV file to read from
     * @param rowIndex the index of the row (0-based)
     * @param columnName the name of the column to retrieve the value from
     * @return the value at the specified row and column
     * @throws IOException if an I/O error occurs
     */
    public String getValueFromCsv(String fileName, int rowIndex, String columnName) throws IOException {
        List<Map<String, String>> csvData = readCsvFromFile(fileName);
        return csvData.get(rowIndex).get(columnName);
    }
}
