package by.derevitsky;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RunnerWeb {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        String url ="http://localhost:8080/department-rest/employees/3";

        //String result = restTemplate.getForObject(url, String.class);
        Employee employee = restTemplate.getForObject(url, Employee.class);
        System.out.println(employee.toString());

        url ="http://localhost:8080/department-rest/departments/2";
        Department department = restTemplate.getForObject(url, Department.class);
        System.out.println("\n" + department.toString() + "\n");

        url ="http://localhost:8080/department-rest/employees/dep/3";
        ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity(url, Employee[].class);
        Employee[] employees = responseEntity.getBody();
        for (Employee emp : employees) {
            System.out.println(emp.toString());
        }

    }
}
