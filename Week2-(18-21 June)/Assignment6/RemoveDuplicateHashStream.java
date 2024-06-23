package Assignment6;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class RemoveDuplicateHashStream {
    private static final String DIRECTORY_PATH = "Assignment6/sample/";

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

            List<String> lines = reader.lines().collect(Collectors.toList()); // convert into list
            System.out.println(lines);
            if (lines.isEmpty()) {
                throw new IOException("The input file is empty.");
            }
    
            writer.write(lines.get(0));
            writer.newLine();

            String[] headers = lines.get(0).split(",");
            System.out.println(headers);
            int keyFieldIndex = Arrays.asList(headers).indexOf(keyField);
            if (keyFieldIndex == -1) {
                throw new IllegalArgumentException("Key field not found in the header.");
            }

            Set<String> uniqueKeys = new HashSet<>();
            lines.stream().skip(1)
                    .filter(line -> {
                        String[] fields = line.split(",");
                        return fields.length > keyFieldIndex && uniqueKeys.add(fields[keyFieldIndex]);
                    })
                    .forEach(line -> {
                        try {
                            writer.write(line);
                            writer.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            System.out.println("Removed duplicates from .csv successfully");
        }
    }

    private static void removeDuplicatesFromTXT(String inputFile, String outputFile, int keyFieldIndex)
            throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputFile));
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile))) {

            Set<String> uniqueKeys = new HashSet<>();
            reader.lines()
                    .filter(line -> {
                        String[] fields = line.split("\\s+");
                        return fields.length > keyFieldIndex && uniqueKeys.add(fields[keyFieldIndex]);
                    })
                    .forEach(line -> {
                        try {
                            writer.write(line);
                            writer.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            System.out.println("Removed duplicates from .txt successfully");
        }
    }
}
