package by.derevitsky;

import by.derevitsky.model.Employee;

// --------- Junit 4 ---------
//import org.junit.Test;
//import org.junit.Assert;
//import org.junit.Ignore;
//import org.junit.FixMethodOrder;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;

// --------- Junit 5 ---------
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

// ---------  ---------
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;          // for junit 5
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;       // for junit 4

import java.time.LocalDate;
import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)       // junit 4
@ExtendWith(SpringExtension.class)              // junit 5
@ContextConfiguration("classpath:dao_context.xml")
@ActiveProfiles(profiles = {"jdbc", "h2mem"})
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)     // junit 4
@TestMethodOrder(MethodOrderer.Alphanumeric.class)  // junit 5
public class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    public void test1_GetAll() throws Exception {
        List<Employee> employees = employeeService.getAll();
        assertNotNull(employees);
        assertEquals(5, employees.size());
    }

    @Test
    public void test2_GetById() throws Exception {
        Employee employee = employeeService.getById(4);
        assertEquals(3, employee.getIdDepartment());
        assertEquals("Alexander", employee.getFirstName());
        assertEquals("Sergeevich", employee.getMiddleName());
        assertEquals("Pushkin", employee.getLastName());
        assertEquals(LocalDate.parse("1991-05-26"), employee.getBirthDate());
        assertEquals(5000, employee.getSalary());
    }

    @Test
    public void test3_GetByDepartmentId() throws Exception {
        List<Employee> employeesInDep = employeeService.getByDepartmentId(1);   // Employees in Department with id=1
        assertEquals(1, employeesInDep.size());
        employeesInDep = employeeService.getByDepartmentId(2);                  // Employees in Department with id=2
        assertEquals(2, employeesInDep.size());
        employeesInDep = employeeService.getByDepartmentId(4);                  // Employees in Department with id=4
        assertEquals(0, employeesInDep.size());
    }

    @Test
    public void test4_Insert() throws Exception {
        employeeService.insert(new Employee(0, 4, "Test", "Testovich", "Tester",
                LocalDate.parse("1900-01-01"), 500));
        List<Employee> employees = employeeService.getAll();
        Employee lastEmployee = employees.get(employees.size() - 1);        // Last Employee after inserting
        int lastId = lastEmployee.getId();
        assertEquals(4, employeeService.getById(lastId).getIdDepartment());
        assertEquals("Test", employeeService.getById(lastId).getFirstName());
        assertEquals("Testovich", employeeService.getById(lastId).getMiddleName());
        assertEquals("Tester", employeeService.getById(lastId).getLastName());
        assertEquals(LocalDate.parse("1900-01-01"), employeeService.getById(lastId).getBirthDate());
        assertEquals(500, employeeService.getById(lastId).getSalary());
    }

    @Test
    public void test5_Update() throws Exception {
        List<Employee> employees = employeeService.getAll();
        Employee lastEmployee = employees.get(employees.size() - 1);
        int lastId = lastEmployee.getId();
        Employee updatedEmployee = new Employee(lastId, 1,
                "UTest", "UTestovich", "Updated-Tester",
                LocalDate.parse("2000-02-02"), 3000);
        employeeService.update(updatedEmployee);                         //  Doing update
        assertEquals(1, employeeService.getById(lastId).getIdDepartment());
        assertEquals("UTest", employeeService.getById(lastId).getFirstName());
        assertEquals("UTestovich", employeeService.getById(lastId).getMiddleName());
        assertEquals("Updated-Tester", employeeService.getById(lastId).getLastName());
        assertEquals(LocalDate.parse("2000-02-02"), employeeService.getById(lastId).getBirthDate());
        assertEquals(3000, employeeService.getById(lastId).getSalary());
    }

    @Test
    public void test6_Delete() throws Exception {
        List<Employee> employees = employeeService.getAll();
        assertEquals(6, employees.size());       // Number of Employees should be 6
        Employee lastEmployee = employees.get(employees.size() - 1);
        employeeService.delete(lastEmployee.getId());               // Deleting last Employee
        employees = employeeService.getAll();
        assertEquals(5, employees.size());       // Number of Employees should be 5
    }
}
