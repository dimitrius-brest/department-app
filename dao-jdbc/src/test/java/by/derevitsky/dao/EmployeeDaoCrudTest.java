package by.derevitsky.dao;

import by.derevitsky.dao.EmployeeDAO;
import by.derevitsky.model.Employee;

// --------- Junit 4 ---------
//import org.junit.Assert;
//import org.junit.FixMethodOrder;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;

// --------- Junit 5 ---------
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class EmployeeDaoCrudTest {

    @Autowired @Qualifier("JdbcEmployeeDAO")
    private EmployeeDAO employeeDAO;

    @Test
    public void test1_GetAll() throws Exception {
        List<Employee> employees = employeeDAO.getAll();
        assertNotNull(employees);                    // The list of Employees is not empty
        assertEquals(5, employees.size());   // The list of Employees contains 4 Departments
    }

    @Test
    public void test2_GetById() throws Exception {
        Employee employee = employeeDAO.getById(1);
        assertEquals(1, employee.getIdDepartment());
        assertEquals("Ivan", employee.getFirstName());
        assertEquals("Ivanovich", employee.getMiddleName());
        assertEquals("Ivanov", employee.getLastName());
        assertEquals(LocalDate.parse("1970-01-01"), employee.getBirthDate());
        assertEquals(10000, employee.getSalary());
    }

    @Test
    public void test3_GetByDepartmentId() throws Exception {
        List<Employee> employeesInDepartment = employeeDAO.getByDepartmentId(1); // Employees in Department with id=1
        assertEquals(1, employeesInDepartment.size());
        employeesInDepartment = employeeDAO.getByDepartmentId(2);               // Employees in Department with id=2
        assertEquals(2, employeesInDepartment.size());
        employeesInDepartment = employeeDAO.getByDepartmentId(4);               // Employees in Department with id=1
        assertEquals(0, employeesInDepartment.size());
    }

    @Test
    public void test4_Insert() throws Exception {
        employeeDAO.insert(new Employee(0, 4, "Test", "Testovich", "Tester",
                LocalDate.parse("1900-01-01"), 500));
        List<Employee> employees = employeeDAO.getAll();
        Employee lastEmployee = employees.get(employees.size() - 1);   // Last Employee after inserting
        int lastId = lastEmployee.getId();
        assertEquals(4, employeeDAO.getById(lastId).getIdDepartment());
        assertEquals("Test", employeeDAO.getById(lastId).getFirstName());
        assertEquals("Testovich", employeeDAO.getById(lastId).getMiddleName());
        assertEquals("Tester", employeeDAO.getById(lastId).getLastName());
        assertEquals(LocalDate.parse("1900-01-01"), employeeDAO.getById(lastId).getBirthDate());
        assertEquals(500, employeeDAO.getById(lastId).getSalary());
    }

    @Test
    public void test5_Update() throws Exception {
        List<Employee> employees = employeeDAO.getAll();
        Employee lastEmployee = employees.get(employees.size() - 1);
        int lastId = lastEmployee.getId();
        Employee updatedEmployee = new Employee(lastId, 1,
                "UTest", "UTestovich", "Updated-Tester",
                LocalDate.parse("2000-02-02"), 3000);
        employeeDAO.update(updatedEmployee);                         //  Doing update
        assertEquals(1, employeeDAO.getById(lastId).getIdDepartment());
        assertEquals("UTest", employeeDAO.getById(lastId).getFirstName());
        assertEquals("UTestovich", employeeDAO.getById(lastId).getMiddleName());
        assertEquals("Updated-Tester", employeeDAO.getById(lastId).getLastName());
        assertEquals(LocalDate.parse("2000-02-02"), employeeDAO.getById(lastId).getBirthDate());
        assertEquals(3000, employeeDAO.getById(lastId).getSalary());
    }

    @Test
    public void test6_Delete() throws Exception {
        List<Employee> employees = employeeDAO.getAll();
        Employee lastEmployee = employees.get(employees.size() - 1);
        assertEquals(6, employees.size());       // Number of Employees should be 6
        employeeDAO.delete(lastEmployee.getId());               // Deleting last Employee
        employees = employeeDAO.getAll();
        assertEquals(5, employees.size());       // Number of Employees should be 5
    }
}
