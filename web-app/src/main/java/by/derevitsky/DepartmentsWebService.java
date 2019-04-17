package by.derevitsky;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer for Web module. Consumes REST API and redirects data to Web Conroller.
 */
@Service
public class DepartmentsWebService {

    private String applicationURL = "http://localhost:8080/department-rest";

    /**
     * Gets Departments from REST API,
     * adds "average salary" and "has employees" flag to every Department in list.
     * @return the list of Departments + average salary + has employees flag
     */
    public List<DepartmentForView> getDepartments(){
        List<DepartmentForView> departmentsForView = new ArrayList<DepartmentForView>();

        // Getting array of Departments form REST API
        RestTemplate restTemplate = new RestTemplate();
        String url = applicationURL+"/departments/all";
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
        return departmentsForView;
    }

    /**
     * Deletes the Department with "id"
     * @param id
     */
    public void deleteDepartment(Integer id){
        RestTemplate restTemplate = new RestTemplate();
        String url = applicationURL+"/departments/" + id;
        restTemplate.delete(url);
    }
}
