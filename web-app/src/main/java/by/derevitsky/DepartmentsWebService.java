package by.derevitsky;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
     * Get Departments from REST API,
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
     * Get the Department with "id" via REST API
     * @param id
     * @return
     */
    public Department getDepartmentById(Integer id){
        RestTemplate restTemplate = new RestTemplate();
        String url = applicationURL+"/departments/" + id;
        ResponseEntity<Department> responseEntity = restTemplate.getForEntity(url, Department.class);
        Department department = responseEntity.getBody();
        // Department department = new Department(333, "Test Department");
        return department;
    }

    /**
     * Add the Department via REST API
     * @param department
     */
    public void addDepartment(Department department){
        RestTemplate restTemplate = new RestTemplate();
        String url = applicationURL+"/departments/add";
        /*HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\"id\":"+ department.getId() +",\"name\":\""+ department.getName() +"\"}";
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        String answer = restTemplate.postForObject(url, entity, String.class);*/
        restTemplate.postForObject(url, department, Department.class);
    }

    /**
     * Update the Department via REST API
     * @param department
     */
    public void updateDepartment(Department department){
        RestTemplate restTemplate = new RestTemplate();
        String url = applicationURL+"/departments/update";
        restTemplate.put(url, department);
    }


    /**
     * Delete the Department with "id" via REST API
     * @param id
     */
    public void deleteDepartment(Integer id){
        RestTemplate restTemplate = new RestTemplate();
        String url = applicationURL+"/departments/" + id;
        restTemplate.delete(url);
    }
}
