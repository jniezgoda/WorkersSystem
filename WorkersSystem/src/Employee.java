public class Employee {
    private String firstName;
    private String lastName;
    private String position;
    private int employeeID;
    private int salary;
    private static int idCounter = 0;

    public Employee(String firstName, String lastName, String position,  int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.employeeID = idCounter++;
        this.salary = salary;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getPosition() {
        return position;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", employeeID=" + employeeID +
                ", salary=" + salary +
                '}';
    }




    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }
}