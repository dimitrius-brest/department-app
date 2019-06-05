package by.derevitsky.web.service;

import by.derevitsky.Department;
import by.derevitsky.web.model.DepartmentForView;
import by.derevitsky.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Departments Service layer for Web module. Consumes REST API and redirects data to Web Conroller.
 */
@Service
public class DepartmentsWebService {

    @Autowired
    private RestTemplate restTemplate;

    private String applicationURL = "http://localhost:8080/department-rest";

    /**
     * Get Departments from REST API,
     * adds "average salary" and "has employees" flag to every Department in list.
     * @return the list of Departments + average salary + has employees flag
     */
    public List<DepartmentForView> getDepartments(){
        List<DepartmentForView> departmentsForView = new ArrayList<DepartmentForView>();

        // Getting array of Departments form REST API
        String url = applicationURL+"/departments/all";

        try {                       // If the list of Departments is not empty
            ResponseEntity<Department[]> responseEntity = restTemplate.getForEntity(url, Department[].class);
            Department[] departments = responseEntity.getBody();

            // Counting average salary for each Department in array
            boolean hasEmployees;
            int averageSalary;

            for(Department dep : departments) {
                String url2 = applicationURL+"/employees/dep/" + dep.getId();
                // Trying to get the list of Employees in the Department
                try {
                    ResponseEntity<Employee[]> responseEntity2 = restTemplate.getForEntity(url2, Employee[].class);
                    Employee[] employees = responseEntity2.getBody();
                    hasEmployees = true;
                    int sum = 0;
                    // Counting average salary of Employees in the Department
                    for (int i = 0; i < employees.length; i++) {
                        sum += employees[i].getSalary();
                    }
                    averageSalary = sum / employees.length;
                } catch (Exception e) {
                    hasEmployees = false;
                    averageSalary = 0;
                }
                departmentsForView.add(new DepartmentForView(dep, averageSalary, hasEmployees));
            }

        } catch (Exception e) {                 // If the list of Departments is empty
            // departments = null;
        }
        return departmentsForView;
    }

    /**
     * Get the Department with "id" via REST API
     * @param id
     * @return
     */
    public Department getDepartmentById(Integer id){
        String url = applicationURL+"/departments/" + id;
        ResponseEntity<Department> responseEntity = restTemplate.getForEntity(url, Department.class);
        Department department = responseEntity.getBody();
        return department;
    }

    /**
     * Add the Department via REST API
     * @param department
     */
    public Department addDepartment(Department department){
        String url = applicationURL+"/departments/add";
        return restTemplate.postForObject(url, department, Department.class);
    }

    /**
     * Update the Department via REST API
     * @param department
     */
    public void updateDepartment(Department department){
        String url = applicationURL+"/departments/update";
        restTemplate.put(url, department);
    }


    /**
     * Delete the Department with "id" via REST API
     * @param id
     */
    public void deleteDepartment(Integer id){
        String url = applicationURL+"/departments/" + id;
        restTemplate.delete(url);
    }
}
