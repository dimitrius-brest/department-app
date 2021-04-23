package by.derevitsky.soap.model;

import by.derevitsky.model.Employee;

import java.time.LocalDate;

/**
 * Additional class for {@link Employee} to convert LocalDate to String and vise versa
 */
public class EmployeeForSoap {
    //Employee employee;

    private int id;
    private int idDepartment;
    private String firstName;
    private String middleName;
    private String lastName;
    private String birthDateString; //  Should be like "yyyy-MM-dd"
    private int salary;

    public EmployeeForSoap() {
    }

    public EmployeeForSoap(int id, int idDepartment,
                           String firstName, String middleName, String lastName,
                           String birthDateString, int salary) {
        this.id = id;
        this.idDepartment = idDepartment;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDateString = birthDateString;
        this.salary = salary;
    }

    // Constructor to convert Employee to EmployeeForSoap
    public EmployeeForSoap(Employee employee) {
        this.id = employee.getId();
        this.idDepartment = employee.getIdDepartment();
        this.firstName = employee.getFirstName();
        this.middleName = employee.getMiddleName();
        this.lastName = employee.getLastName();
        this.birthDateString = employee.getBirthDate().toString();
        this.salary = employee.getSalary();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDateString() {
        return birthDateString;
    }

    public void setBirthDateString(String birthDateString) {
        this.birthDateString = birthDateString;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeeForSoap{" +
                "id=" + id +
                ", idDepartment=" + idDepartment +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDateString='" + birthDateString + '\'' +
                ", salary=" + salary +
                '}';
    }

    //  Method to convert EmployeeForSoap to Employee
    public Employee convertToEmployee() {
        Employee employee = new Employee(
                this.id, this.idDepartment,
                this.firstName, this.middleName, this.lastName,
                LocalDate.parse(this.birthDateString), this.salary);
        return employee;
    }
}
