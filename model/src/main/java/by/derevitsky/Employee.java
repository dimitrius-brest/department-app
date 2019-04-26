package by.derevitsky;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Model of "Employee" entity
 * @author Dmitry Derevitsky
 */
public class Employee {
    private int id;
    private int idDepartment;
    private String firstName;
    private String middleName;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private int salary;

    public Employee() {
    }

    public Employee(int id, int idDepartment,
                    String firstName, String middleName, String lastName,
                    LocalDate birthDate, int salary) {
        this.id = id;
        this.idDepartment = idDepartment;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.salary = salary;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getIdDepartment() { return idDepartment; }

    public void setIdDepartment(int idDepartment) { this.idDepartment = idDepartment; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getMiddleName() { return middleName; }

    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public LocalDate getBirthDate() { return birthDate; }

    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public int getSalary() { return salary; }

    public void setSalary(int salary) { this.salary = salary; }

    @Override
    public String toString(){
        return "id=" + this.id + " dep=" + this.idDepartment
                + ";   FIO: " + this.firstName + " " + this.middleName + " " + this.lastName
                + ";   birth date: " + this.birthDate + "; salary: " + this.salary;
    }

}
