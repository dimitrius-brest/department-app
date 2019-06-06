package by.derevitsky.web;

import by.derevitsky.Employee;
import by.derevitsky.web.service.EmployeesWebService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeWebServiceTest {

    // See how to mock RestTemplate: https://www.baeldung.com/spring-mock-rest-template
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    EmployeesWebService empWebService = new EmployeesWebService();

    private String applicationURL = "http://localhost:8080/department-rest";

    private Employee[] mockEmployees = new Employee[]{
            new Employee(1, 1, "Test1",
                    "Testovich1", "Tester1", LocalDate.parse("1991-01-01"), 500),
            new Employee(2, 1, "Test2",
                    "Testovich2", "Tester2", LocalDate.parse("1992-02-02"), 600),
            new Employee(3, 2, "Test3",
                    "Testovich3", "Tester3", LocalDate.parse("1993-03-03"), 700)
    };

    private List<Employee> findInMockEmployeesByDepId(Integer depId){
        List<Employee> foundEmployees = new ArrayList<Employee>();
        for(Employee employee : mockEmployees){
            if(employee.getIdDepartment() == depId) foundEmployees.add(employee);
        }
        return foundEmployees;
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee mockEmployee = new Employee(1, 1, "Test",
                "Testovich", "Tester", LocalDate.parse("1999-09-09"), 1000);
        Mockito
                .when(restTemplate.getForEntity(applicationURL+"/employees/1", Employee.class))
                .thenReturn(new ResponseEntity(mockEmployee, HttpStatus.OK));
        Employee employee = empWebService.getEmployeeById(1);
        Assert.assertEquals(mockEmployee, employee);
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        Mockito
                .when(restTemplate.getForEntity(applicationURL+"/employees/all", Employee[].class))
                .thenReturn(new ResponseEntity(mockEmployees, HttpStatus.OK));
        int totalNumberOfEmployees = empWebService.getAllEmployees().size();
        Assert.assertEquals(3, totalNumberOfEmployees);
    }

    @Test
    public void testGetEmployeesByDepartmentId() throws Exception{
        Employee[] employeesInDep1 = findInMockEmployeesByDepId(1).toArray(
                new Employee[findInMockEmployeesByDepId(1).size()]);

        Employee[] employeesInDep2 = findInMockEmployeesByDepId(2).toArray(
                new Employee[findInMockEmployeesByDepId(2).size()]);

        //Employee[] employeesInDep3 = new Employee[0];

        Mockito
                .when(restTemplate.getForEntity(applicationURL+"/employees/dep/1", Employee[].class))
                .thenReturn(new ResponseEntity(employeesInDep1, HttpStatus.OK));
        Mockito
                .when(restTemplate.getForEntity(applicationURL+"/employees/dep/2", Employee[].class))
                .thenReturn(new ResponseEntity(employeesInDep2, HttpStatus.OK));
        Mockito
                .when(restTemplate.getForEntity(applicationURL+"/employees/dep/3", Employee[].class))
                .thenThrow(HttpClientErrorException.class);

        // Number of Employees in Department #1
        Assert.assertEquals(2, empWebService.getEmployeesByDepartmentId(1).size());
        // Number of Employees in Department #2
        Assert.assertEquals(1, empWebService.getEmployeesByDepartmentId(2).size());
        // Number of Employees in Department #3 should be 0
        Assert.assertEquals(0, empWebService.getEmployeesByDepartmentId(3).size());
    }

    @Test
    public void testAddEmployee() throws Exception {
        Employee newEmployee = new Employee(1, 1, "Test",
                "Testovich", "New-Tester", null, 1000);
        Mockito
                .when(restTemplate.postForObject(applicationURL+"/employees/add", newEmployee, Employee.class))
                .thenReturn(newEmployee);

        Employee employee = empWebService.addEmployee(newEmployee);
        Assert.assertEquals(LocalDate.parse("2000-01-01"), employee.getBirthDate());
        Assert.assertEquals("New-Tester", employee.getLastName());
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        Employee updatedEmployee = new Employee(1, 2, "Test",
                "Testovich", "Updated-Tester", null, 2000);
        empWebService.updateEmployee(updatedEmployee);
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        empWebService.deleteEmployee(1);
    }
}
