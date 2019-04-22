package by.derevitsky.web.service;

import by.derevitsky.Employee;
import org.springframework.stereotype.Service;

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
        return new ArrayList<Employee>();
    }
}
