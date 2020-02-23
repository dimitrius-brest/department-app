package by.derevitsky.web;

import by.derevitsky.model.Department;
import by.derevitsky.model.Employee;
import by.derevitsky.web.model.DepartmentForView;
import by.derevitsky.web.service.DepartmentsWebService;

// --------- Junit 4 ---------
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;

// --------- Junit 5 ---------
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

// --------- Mockito ---------
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;          // junit 4
import org.mockito.junit.jupiter.MockitoExtension;      // junit 5

// ---------  ---------
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)         // junit 5
//@RunWith(MockitoJUnitRunner.class)        // junit 4
public class DepartmentsWebServiceTest {

    // See how to mock RestTemplate: https://www.baeldung.com/spring-mock-rest-template
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    DepartmentsWebService depWebService = new DepartmentsWebService();

    private String applicationURL = "http://localhost:8080/department-rest";

    // -------- Tests -------------
    @Test
    public void testGetDepartments() throws Exception {

        // -------  Get Departments --------------
        Department[] mockDepartments = {
                new Department(1, "Test Department 1"),
                new Department(2, "Test Department 2"),
                new Department(3, "Test Department 3")    };
        Mockito
                .when(restTemplate.getForEntity(applicationURL+"/departments/all", Department[].class))
                .thenReturn(new ResponseEntity<Department[]>(mockDepartments, HttpStatus.OK));


        //  -------  Get Employees to count average salary --------------

        Employee[] mockEmployeesInDep1 = {
                new Employee(1, 1, "Name1", null, "Last1",
                        LocalDate.parse("1991-01-01"), 1000),
                new Employee(2, 1, "Name2", null, "Last2",
                        LocalDate.parse("1992-02-02"), 2000)};
        Employee[] mockEmployeesInDep2 = {
                new Employee(3, 2, "Name3", null, "Last2",
                        LocalDate.parse("1993-03-03"), 3000),
                new Employee(4, 2, "Name4", null, "Last4",
                        LocalDate.parse("1994-04-04"), 4000)};

        Mockito
                .when(restTemplate.getForEntity(applicationURL+"/employees/dep/1", Employee[].class))
                .thenReturn(new ResponseEntity<Employee[]>(mockEmployeesInDep1, HttpStatus.OK));
        Mockito
                .when(restTemplate.getForEntity(applicationURL+"/employees/dep/2", Employee[].class))
                .thenReturn(new ResponseEntity<Employee[]>(mockEmployeesInDep2, HttpStatus.OK));

        // ---------  Commit and assert
        List<DepartmentForView> departmentsForView = depWebService.getDepartments();

        assertEquals("Test Department 1", departmentsForView.get(0).getName());
        assertEquals(1500, departmentsForView.get(0).getAverageSalary());        // Average salary
        assertTrue(departmentsForView.get(0).isHasEmployees());                          // Has Employees

        assertEquals("Test Department 2", departmentsForView.get(1).getName());
        assertEquals(3500, departmentsForView.get(1).getAverageSalary());        // Average salary
        assertTrue(departmentsForView.get(1).isHasEmployees());                          // Has Employees

        assertEquals("Test Department 3", departmentsForView.get(2).getName());
        assertEquals(0, departmentsForView.get(2).getAverageSalary());           // Average salary
        assertFalse(departmentsForView.get(2).isHasEmployees());                         // Should not have Employees

        // -------  Test empty list of Departments --------------
        mockDepartments = null;
        Mockito
                .when(restTemplate.getForEntity(applicationURL+"/departments/all", Department[].class))
                .thenReturn(new ResponseEntity<Department[]>(mockDepartments, HttpStatus.OK));

        departmentsForView = depWebService.getDepartments();
        assertEquals(0, departmentsForView.size());

    }

    @Test
    public void testGetDepartmentById() throws Exception {
        Department mockDepartment = new Department(1, "Test Department");
        Mockito
                .when(restTemplate.getForEntity(applicationURL+"/departments/1", Department.class))
                .thenReturn(new ResponseEntity(mockDepartment, HttpStatus.OK));

        Department department = depWebService.getDepartmentById(1);
        assertEquals(mockDepartment, department);
    }

    @Test
    public void testAddDepartment() throws Exception {
        Department newDepartment = new Department(2, "New Test Department");

        Mockito
                .when(restTemplate.postForObject(applicationURL+"/departments/add", newDepartment, Department.class))
                .thenReturn(newDepartment);
        Department department = depWebService.addDepartment(newDepartment);
        assertEquals(2, department.getId());
        assertEquals("New Test Department", department.getName());
    }

    @Test
    public void testUpdateDepartment() throws Exception {
        Department updatedDepartment = new Department(2, "Updated Test Department");
        depWebService.updateDepartment(updatedDepartment);
    }

    @Test
    public void testDeleteDepartment() throws Exception {
        depWebService.deleteDepartment(2);
    }
}
