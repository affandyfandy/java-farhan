package aliramadhan.Manager;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;
import java.util.stream.Collectors;

import aliramadhan.Models.Employee;
import aliramadhan.Utils.DateUtils;
import aliramadhan.Utils.FileUtils;

public class AppManager {
    private static AppManager instance;
    private List<Employee> employees;
    private final Scanner scanner;
    private boolean useOpenCSV;

    private AppManager() {
        employees = new ArrayList<>();
        scanner = new Scanner(System.in);
        useOpenCSV = false;
    }

    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    // public void doSomething() {
    // // Implementasi metode
    // System.out.println("AppManager melakukan sesuatu!");
    // }

    public void start() {
        headers();
        while (true) {
            showMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                System.out.println("--------------------------------------------------------------------");
                switch (choice) {
                    case 0 -> exit();
                    case 1 -> importData();
                    case 2 -> addEmployee();
                    case 3 -> printFilteredEmployees();
                    case 4 -> exportFilteredEmployees();
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static void headers() {
        System.out.println("------------------------");
        System.out.println("Welcome to Application");
        System.out.println("------------------------");
    }

    private static void showMenu() {
        System.out.println("====================================================================");
        System.out.println("List Menu Application:");
        System.out.println("0 - Exit");
        System.out.println("1 - Select File, Import data");
        System.out.println("2 - Add new Employee");
        System.out.println("3 - Filter Employees");
        System.out.println("4 - Export filtered Employees");
        System.out.print("Choose an option: ");
    }

    private static void exit() {
        System.out.println("See youu again");
        System.exit(0);
    }

    private void importData() {
        System.out.println("Select method for read CSV: ");
        System.out.println("1 - Manual");
        System.out.println("2 - OpenCSV");
        System.out.print("Choose an option: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            switch (choice) {
                case 1 -> useOpenCSV = false;
                case 2 -> useOpenCSV = true;
                default -> {
                    System.out.println("Invalid choice. Defaulting to manual reading.");
                    useOpenCSV = false;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Defaulting to manual reading.");
            useOpenCSV = false;
        }

        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();
        if (useOpenCSV) {
            employees = FileUtils.readEmployeesFromCSVOpenCSV(filePath);
        } else {
            employees = FileUtils.readEmployeesFromCSVManual(filePath);
        }
    }

    private void addEmployee() {
        try {
            System.out.println("Input All Data");
            System.out.println("====================================================================");
            System.out.println("Enter Employee ID: ");
            String id = scanner.nextLine().trim();
            Employee existingEmployee = employees.stream().filter(e -> e.getId().equalsIgnoreCase(id)).findFirst()
                    .orElse(null);
            if (existingEmployee != null) {
                System.out.println("Employee with ID " + id + " already exists.");
                System.out.print("Do you want to overwrite this employee id? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (!response.equals("yes")) {
                    System.out.println("Addition cancelled.");
                    return;
                }
                // Remove existing employee if overwrite is chosen
                employees.remove(existingEmployee);
            }
            System.out.println("Enter Employee Name: ");
            String name = scanner.nextLine().trim();
            System.out.println("Enter Employee Date of Birth (d/M/yyyy): ");
            String dobString = scanner.nextLine().trim();
            System.out.println("Enter Employee Address: ");
            String address = scanner.nextLine().trim();
            System.out.println("Enter Employee Department: ");
            String department = scanner.nextLine().trim();
            // make new instance employ
            Employee employee = new Employee(id, name, DateUtils.parseDate(dobString), address, department);
            // input into list
            employees.add(employee);
            System.out.println("Employee added successfully.");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use the format d/M/yyyy.");
        } catch (Exception e) {
            System.out
                    .println("An error occurred while adding the employee. Please try again.");
        }
    }

    private void showOptionFilter() {
        System.out.println("=======================================");
        System.out.println("Filter by: ");
        System.out.println("0 - All Data");
        System.out.println("1 - Name");
        System.out.println("2 - ID");
        System.out.println("3 - Date of Birth (year)");
        System.out.println("4 - Department");
        System.out.print("Choose an option: ");
        System.out.println("=======================================");
    }

    private List<Employee> filterEmployees() {
        showOptionFilter();
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());

            List<Employee> filtered = new ArrayList<>();
            switch (choice) {
                case 0 -> {
                    filtered = employees.stream()
                            .collect(Collectors.toList());
                }
                case 1 -> {
                    System.out.print("Enter name pattern: ");
                    String namePattern = scanner.nextLine();
                    filtered = employees.stream()
                            .filter(e -> e.getName().contains(namePattern))
                            .collect(Collectors.toList());
                }
                case 2 -> {
                    System.out.print("Enter ID pattern: ");
                    String idPattern = scanner.nextLine();
                    filtered = employees.stream()
                            .filter(e -> e.getId().contains(idPattern))
                            .collect(Collectors.toList());
                }
                case 3 -> {
                    System.out.print("Enter year of birth: ");
                    int year = Integer.parseInt(scanner.nextLine().trim());
                    filtered = employees.stream()
                            .filter(e -> e.getDateOfBirth().getYear() == year)
                            .collect(Collectors.toList());
                }
                case 4 -> {
                    System.out.print("Enter Department pattern: ");
                    String departement = scanner.nextLine();
                    filtered = employees.stream()
                            .filter(e -> e.getDepartment().contains(departement))
                            .collect(Collectors.toList());
                }
                default -> System.out.println("Invalid choice. Returning to menu.");
            }
            return filtered;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return new ArrayList<>();
        }
    }

    private void printFilteredEmployees() {
        List<Employee> listOfEmployee = filterEmployees();
        System.out.println("\nShowing " + listOfEmployee.size() + " employee(s) data...");
        for (Employee e : listOfEmployee) {
            System.out.println(e.toStringCSV());
        }
        System.out.println("\nShowing " + listOfEmployee.size() + " employee(s) data...");

    }

    private void exportFilteredEmployees() {
        List<Employee> listOfEmployee = filterEmployees().stream()
                .sorted((e1, e2) -> e1.getDateOfBirth().compareTo(e2.getDateOfBirth()))
                .collect(Collectors.toList());
        System.out.print("Enter the file name to save (or provide full path): ");
        String filePath = scanner.nextLine();
        FileUtils.writeEmployeesToCSV(listOfEmployee, filePath);
    }

}
