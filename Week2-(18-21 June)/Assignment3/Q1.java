package Assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        /*
         * Single Resources
         */
        // try (BufferedReader br = new BufferedReader(new
        // FileReader("Assignment3/test.txt"))) {
        // String line;
        // while ((line = br.readLine()) != null) {
        // System.out.println(line);
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        // try (PrintWriter writer = new PrintWriter(new
        // File("Assignment3/lib/test.txt"))) {
        // writer.println("Hello World");
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        /*
         * Multiple Resources
         */
        // try (Scanner scanner = new Scanner(new File("Assignment3/lib/test.txt"));
        // PrintWriter writer = new PrintWriter(new File("Assignment3/lib/test2.txt")))
        // {
        // while (scanner.hasNextLine()) {
        // writer.println(scanner.nextLine());
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

    }

}
