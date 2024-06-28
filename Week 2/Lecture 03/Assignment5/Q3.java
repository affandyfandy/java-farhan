package Assignment5;

import java.io.*;
import java.nio.file.*;
import java.util.*;
// import java.util.stream.*;

public class Q3 {
    private static final String DIRECTORY_PATH = "Assignment5/sample/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter input file path: ");
        String inputFileName = scanner.nextLine();
        String inputFile = DIRECTORY_PATH + inputFileName;

        System.out.print("Enter output file path: ");
        String outputFileName = scanner.nextLine();
        String outputFile = DIRECTORY_PATH + outputFileName;

        System.out.print("Enter file type (csv/txt): ");
        String fileType = scanner.nextLine().toLowerCase();

        System.out.print("Enter key field (for csv: column name, for txt: column index): ");
        String keyField = scanner.nextLine();

        try {
            if ("csv".equalsIgnoreCase(fileType)) {
                removeDuplicatesFromCSV(inputFile, outputFile, keyField);
            } else if ("txt".equalsIgnoreCase(fileType)) {
                int keyFieldIndex = Integer.parseInt(keyField);
                removeDuplicatesFromTXT(inputFile, outputFile, keyFieldIndex);
            } else {
                System.out.println("Invalid file type. Please specify 'csv' or 'txt'.");
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        scanner.close();
    }

    private static void removeDuplicatesFromCSV(String inputFile, String outputFile, String keyField)
            throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputFile));
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile))) {

            String headerLine = reader.readLine();
            if (headerLine == null) {
                throw new IOException("The input file is empty.");
            }

            writer.write(headerLine);
            writer.newLine();

            String[] headers = headerLine.split(",");
            int keyFieldIndex = Arrays.asList(headers).indexOf(keyField);
            if (keyFieldIndex == -1) {
                throw new IllegalArgumentException("Key field '" + keyField + "' not found in the header.");
            }

            Set<String> uniqueKeys = new HashSet<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = splitCSVLine(line);
                if (fields.length > keyFieldIndex) {
                    String key = fields[keyFieldIndex];
                    if (uniqueKeys.add(key)) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }
        }
    }

    private static String[] splitCSVLine(String line) {
        List<String> fieldList = new ArrayList<>();
        StringBuilder fieldBuilder = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                // Toggle quote state
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                // If not in quotes and encounter a comma, add current field to list
                fieldList.add(fieldBuilder.toString());
                fieldBuilder.setLength(0); // Clear the string builder for next field
            } else {
                // Otherwise, append character to current field
                fieldBuilder.append(c);
            }
        }
        // Add the last field
        fieldList.add(fieldBuilder.toString());

        // Remove leading and trailing whitespace from each field
        List<String> trimmedFields = new ArrayList<>();
        for (String field : fieldList) {
            trimmedFields.add(field.trim());
        }

        return trimmedFields.toArray(new String[0]);
    }

    private static void removeDuplicatesFromTXT(String inputFile, String outputFile, int keyFieldIndex)
            throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputFile));
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile))) {

            Set<String> uniqueKeys = new HashSet<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\s+");
                if (fields.length > keyFieldIndex) {
                    String key = fields[keyFieldIndex];
                    if (uniqueKeys.add(key)) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }
        }
        System.out.println("Remove duplicate .txt successfully");
    }
}
