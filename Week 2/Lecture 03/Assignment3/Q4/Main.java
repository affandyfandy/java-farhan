package Assignment3.Q4;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[] menu = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
        Scanner scanner = new Scanner(System.in);

        while (true) {
            showMenu(menu);
            try {
                System.out.print("Enter a number between 1 and 5 to display menu item (or 0 to exit): ");
                int choice = scanner.nextInt();

                if (choice == 0) {
                    System.out.println("Exiting the program.");
                    break;
                }

                if (choice < 1 || choice > 5) {
                    throw new Lab2Exception("Invalid choice. Please enter a number between 1 and 5.");
                }

                System.out.println("\nMenu Item " + choice + ": " + menu[choice - 1]);
            } catch (Lab2Exception e) {
                System.out.println("Lab2Exception caught: " + e.getMessage());
                // Optionally, you can log the exception or perform other actions here.
            } catch (Exception e) {
                System.out.println("Exception caught: " + e);
                scanner.nextLine(); // clear the buffer
            }
        }

        scanner.close();
    }

    private static void showMenu(String[] menu) {
        System.out.println("\nMenu:");
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". " + menu[i]);
        }
        System.out.println();
    }
}
