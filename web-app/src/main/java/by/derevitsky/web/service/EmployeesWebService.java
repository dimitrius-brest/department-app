package by.derevitsky.web.service;

import by.derevitsky.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Employees Service layer for Web module. Consumes REST API and redirects data to Web Conroller.
 */
@Service
public class EmployeesWebService {

    private String applicationURL = "http://localhost:8080/department-rest";

    /**
     * Get Employees from certain Department by "Department ID" via REST API
     * @param depId
     * @return the list of Employees of the Department
     */
    public List<Employee> getEmployeesByDepartmentId(Integer depId){
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee(1, 1, "Ilon", "Ilonovich", "Mask", LocalDate.parse("1991-01-01"), 10000));
        employees.add(new Employee(2, 2, "Ivan", "Ivanovich", "Ivanov", LocalDate.parse("1992-02-20"), 2000));
        employees.add(new Employee(3, 2, "Petr", "Petrovich", "Petrov", LocalDate.parse("1993-03-30"), 4000));
        employees.add(new Employee(4, 3, "Alex", "Sergeevich", "Pushkin", LocalDate.parse("1994-04-14"), 1000));
        employees.add(new Employee(5, 3, "John", "", "Smith", LocalDate.parse("1990-12-31"), 1500));

        List<Employee> selectedEmployees = new ArrayList<>();
        for (Employee employee : employees){
            if (employee.getIdDepartment() == depId){
                selectedEmployees.add(employee);
            }
        }
        return selectedEmployees;
    }
}
