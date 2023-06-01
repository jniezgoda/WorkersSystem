import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        HashMap<Integer,Employee> employeeHashMap = new HashMap<>();
        EmployeeManager employeeManager = new EmployeeManager(employeeHashMap);
        employeeManager.addEmployee("John", "Doe", "Manager", 75000);
        employeeManager.addEmployee("Jane", "Smith", "Accountant",  60000);
        employeeManager.addEmployee("Michael", "Johnson", "Accountant", 55000);
        employeeManager.addEmployee("Emily", "Jones", "Marketing Specialist", 50000);
        employeeManager.addEmployee("David", "Brown", "Accountant", 45000);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true)
        {
            System.out.println("1.Dodaj pracownika");
            System.out.println("2.Usuń pracownika");
            System.out.println("3.Zaktualizuj dane o pracowniku");
            System.out.println("4.Wyszukaj pracownika po nazwisku");
            System.out.println("5.Wyświetl liste wszystkich pracownikow");
            System.out.println("Inna wartosc zakonczy program");
            int input = Integer.parseInt(bufferedReader.readLine());
            Scanner scanner = new Scanner(System.in);
            switch (input){
                case 1:
                    String firstName;
                    String lastName;
                    String position1;
                    int salary;



                    System.out.println("Podaj imię nowego pracownika");
                    firstName = scanner.next();
                    System.out.println("Podaj nazwisko nowego pracownika");
                    lastName = scanner.next();
                    System.out.println("Podaj pozycję nowego pracownika");
                    position1 = scanner.next();
                    System.out.println("Podaj wynagrodzenie nowego pracownika");
                    try {
                        salary = scanner.nextInt();
                    } catch (InputMismatchException ex) {
                        salary = 3500;
                        System.out.println("There was an input mismatch and the salary was set to " + salary);
                    }
                    employeeManager.addEmployee(firstName, lastName, position1, salary);
                    break;
                case 2:
                {
                    int id = Integer.parseInt(bufferedReader.readLine());
                    employeeManager.removeEmployee(employeeManager.searchEmployeeByID(id));
                    break;
                }
                case 3:
                {
                    System.out.println("Podaj id pracownika do edycji.");
                    int id = Integer.parseInt(bufferedReader.readLine());
                    String position;
                    Employee newEmployee = employeeManager.searchEmployeeByID(id);

                    System.out.println("Edytujesz pracownika: " + newEmployee.getFirstName() + " " + newEmployee.getLastName());
                    System.out.println("Obecna pozycja tego pracownika to " + newEmployee.getPosition()
                            + " Podaj nową pozycję albo naciśnij Enter, aby pominąć.");
                    String inputPosition = scanner.next();
                    if(!inputPosition.isEmpty()) {
                        position = inputPosition;
                        newEmployee.setPosition(position);
                    }


                    System.out.println("Obecne wynagrodzenie tego pracownika to " + newEmployee.getSalary()
                            + " Podaj nową wartość wynagrodzenia.");
                    int inputSalary = scanner.nextInt();
                    newEmployee.setSalary(inputSalary);
                    employeeManager.updateEmployee(id, newEmployee);



                    break;

                }
                case 4:
                {
                    System.out.println("Podaj nazwisko do wyszukania");
                    String lastName1 = bufferedReader.readLine();
                    HashSet<Employee> employeeHashSet = employeeManager.searchEmployeesByLastName(lastName1);
                    if(employeeHashSet.isEmpty()) {
                        System.out.println("Nie ma pracownika o takim nazwisku.");
                    } else {
                        for (Employee employee: employeeHashSet
                        ) {
                            System.out.println(employee);
                        }
                    }

                    break;

                }
                case 5:
                {
                    employeeManager.listAllEmployees();
                    break;
                }
                default:
                    System.exit(0);
            }
        }
    }
}