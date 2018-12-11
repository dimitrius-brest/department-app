package by.derevitsky;

import org.springframework.web.client.RestTemplate;

public class RunnerWeb {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        String url ="http://localhost:8080/rest-1.0-SNAPSHOT/employees/3";

        //String result = restTemplate.getForObject(url, String.class);
        Employee employee = restTemplate.getForObject(url, Employee.class);
        System.out.println(employee.toString());

        url ="http://localhost:8080/rest-1.0-SNAPSHOT/departments/2";
        Department department = restTemplate.getForObject(url, Department.class);
        System.out.println("\n" + department.toString());
    }
}
