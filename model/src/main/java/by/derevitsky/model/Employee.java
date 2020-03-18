package by.derevitsky.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * Model of "Employee" entity
 * @author Dmitry Derevitsky
 */
@Entity
@Table(name = "employees")
//@Table(name = "EMPLOYEES")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "id_department")
    private int idDepartment;

    @Size(max = 50)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 50)
    @Column(name = "middle_name")
    private String middleName;

    @NotBlank(message = "Last name may not be blank")
    @Size(max = 100)
    @Column(name = "last_name")
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)   // The same as (pattern="yyyy-MM-dd")
    @Past(message = "Birth date must be in the past")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Digits(integer = 10, fraction = 0, message = "Must be integer, no more than 10 digits")
    @Min(value = 0, message="Salary may not be less than 0")
    @Column(name = "salary")
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
        return "\nid=" + this.id + " dep=" + this.idDepartment
                + "; FIO: " + this.firstName + " " + this.middleName + " " + this.lastName
                + ";  birth date: " + this.birthDate + "; salary: " + this.salary;
    }

}
