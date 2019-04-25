package by.derevitsky.web.service;

import by.derevitsky.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Employees Service layer for Web module. Consumes REST API and redirects data to Web Conroller.
 */
@Service
public class EmployeesWebService {

    private String applicationURL = "http://localhost:8080/department-rest";

    /**
     * Get all Employees via REST API
     * @return
     */
    public List<Employee> getAllEmployees(){
        RestTemplate restTemplate = new RestTemplate();
        String url = applicationURL + "/employees/all";
        try {                                   // If the list of Employees is not empty
            ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity(url, Employee[].class);
            Employee[] employees = responseEntity.getBody();
            return Arrays.asList(employees);
        } catch (Exception e){                  // If the list of Employees is empty
            return new ArrayList<Employee>();
        }
    }

    /**
     * Get Employees from certain Department by "Department ID" via REST API
     * @param depId
     * @return the list of Employees of the Department
     */
    public List<Employee> getEmployeesByDepartmentId(Integer depId){
        RestTemplate restTemplate = new RestTemplate();
        String url = applicationURL + "/employees/dep/" + depId;
        try {                                   // If list of Employees is not empty
            ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity(url, Employee[].class);
            Employee[] employees = responseEntity.getBody();
            return Arrays.asList(employees);
        } catch (Exception e) {                 // If list of Employees is empty
            return new ArrayList<Employee>();
        }
    }

    /**
     * Get the Employee with "id" via REST API
     * @param id
     * @return
     */
    public Employee getEmployeeById(Integer id){
        RestTemplate restTemplate = new RestTemplate();
        String url = applicationURL + "/employees/" + id;
        ResponseEntity<Employee> responseEntity = restTemplate.getForEntity(url, Employee.class);
        Employee employee = responseEntity.getBody();
        return employee;
    }

    /**
     * Delete the Employee with "id" via REST API
     * @param id
     */
    public void deleteEmployee(Integer id){
        RestTemplate restTemplate = new RestTemplate();
        String url = applicationURL + "/employees/" + id;
        restTemplate.delete(url);
    }
}
