package by.derevitsky.dao;

import by.derevitsky.model.Employee;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository("JpaEmployeeDAO")
@Profile("jpa-mysql")
public class EmployeeDAOImplJpaMysql implements EmployeeDAO {
    // Stub
    List<Employee> employees = new ArrayList<Employee>(
        Arrays.asList(
            new Employee(1, 1, "Alexandr", "Sergeevich", "Pushkin",
                    LocalDate.parse("2020-02-02"), 500),
            new Employee(2, 2, "Mihail", "Yurjevich", "Lermontov",
                    LocalDate.parse("2020-02-02"), 1000),
            new Employee(3, 2, "Lev", "Nikolaevich", "Tolstoy",
                    LocalDate.parse("2020-02-02"), 1500)
        )
    );

    @Override
    public List<Employee> getAll() {
        return employees;
    }

    @Override
    public Employee getById(int id) {
        Employee searchedEmployee = new Employee();
        for (Employee employee : employees) {
            if (id == employee.getId()) {
                searchedEmployee = employee;
                break;
            }
        }
        return searchedEmployee;
    }

    @Override
    public List<Employee> getByDepartmentId(int departmentId) {
        List<Employee> searchedEmployees = new ArrayList<Employee>();
        for (Employee employee : employees) {
            if (employee.getIdDepartment() == departmentId) {
                searchedEmployees.add(employee);
            }
        }
        return searchedEmployees;
    }

    @Override
    public void insert(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void update(Employee employee) {
        for (Employee updatedEmployee : employees) {
            if (updatedEmployee.getId() == employee.getId()) {
                updatedEmployee.setIdDepartment(employee.getIdDepartment());
                updatedEmployee.setFirstName(   employee.getFirstName());
                updatedEmployee.setMiddleName(  employee.getMiddleName());
                updatedEmployee.setLastName(    employee.getLastName());
                updatedEmployee.setBirthDate(   employee.getBirthDate());
                updatedEmployee.setSalary(      employee.getSalary());
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employees.remove(employee);
                break;
            }
        }
    }
}
