package Assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// use try-with-resources with multiple resources
public class Q3 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("Assignment3/lib/test.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line); // Print each line to console

                // Write to test2.txt
                try (FileWriter fw = new FileWriter("Assignment3/lib/test2.txt", true)) {
                    fw.write(line + "\n"); // Write each line to test2.txt
                } catch (IOException e) {
                    System.err.println("Failed to write to test2.txt: " + e.getMessage());
                }
            }
            System.out.println("Successfully wrote all lines to test2.txt.");
        } catch (IOException e) {
            System.err.println("Error reading from test.txt: " + e.getMessage());
        }
    }
}
