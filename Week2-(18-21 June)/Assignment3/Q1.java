package Assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Q1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("Assignment3/test.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
