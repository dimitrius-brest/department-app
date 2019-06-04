package by.derevitsky.web;

import by.derevitsky.Department;
import by.derevitsky.web.service.DepartmentsWebService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentWebServiceTest {

    // See how to mock RestTemplate: https://www.baeldung.com/spring-mock-rest-template
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    DepartmentsWebService depWebService = new DepartmentsWebService();

    private String applicationURL = "http://localhost:8080/department-rest";

    @Test
    public void testGetDepartmentById() throws Exception {
        Department mockDepartment = new Department(1, "Test Department");
        Mockito
                .when(restTemplate.getForEntity(applicationURL+"/departments/1", Department.class))
                .thenReturn(new ResponseEntity(mockDepartment, HttpStatus.OK));

        Department department = depWebService.getDepartmentById(1);
        Assert.assertEquals(mockDepartment, department);
    }
}
