package by.derevitsky;

import by.derevitsky.model.Employee;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

@Ignore("Temporary...")
@ContextConfiguration("classpath:dao_context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "jdbc-h2")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    public void test1_GetAll() throws Exception {
        List<Employee> employees = employeeService.getAll();
        Assert.assertNotNull(employees);
        Assert.assertEquals(5, employees.size());
    }

    @Test
    public void test2_GetById() throws Exception {
        Employee employee = employeeService.getById(4);
        Assert.assertEquals(3, employee.getIdDepartment());
        Assert.assertEquals("Alexander", employee.getFirstName());
        Assert.assertEquals("Sergeevich", employee.getMiddleName());
        Assert.assertEquals("Pushkin", employee.getLastName());
        Assert.assertEquals(LocalDate.parse("1991-05-26"), employee.getBirthDate());
        Assert.assertEquals(5000, employee.getSalary());
    }

    @Test
    public void test3_GetByDepartmentId() throws Exception {
        List<Employee> employeesInDep = employeeService.getByDepartmentId(1);   // Employees in Department with id=1
        Assert.assertEquals(1, employeesInDep.size());
        employeesInDep = employeeService.getByDepartmentId(2);                  // Employees in Department with id=2
        Assert.assertEquals(2, employeesInDep.size());
        employeesInDep = employeeService.getByDepartmentId(4);                  // Employees in Department with id=4
        Assert.assertEquals(0, employeesInDep.size());
    }

    @Test
    public void test4_Insert() throws Exception {
        employeeService.insert(new Employee(0, 4, "Test", "Testovich", "Tester",
                LocalDate.parse("1900-01-01"), 500));
        List<Employee> employees = employeeService.getAll();
        Employee lastEmployee = employees.get(employees.size() - 1);        // Last Employee after inserting
        int lastId = lastEmployee.getId();
        Assert.assertEquals(4, employeeService.getById(lastId).getIdDepartment());
        Assert.assertEquals("Test", employeeService.getById(lastId).getFirstName());
        Assert.assertEquals("Testovich", employeeService.getById(lastId).getMiddleName());
        Assert.assertEquals("Tester", employeeService.getById(lastId).getLastName());
        Assert.assertEquals(LocalDate.parse("1900-01-01"), employeeService.getById(lastId).getBirthDate());
        Assert.assertEquals(500, employeeService.getById(lastId).getSalary());
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
        Assert.assertEquals(1, employeeService.getById(lastId).getIdDepartment());
        Assert.assertEquals("UTest", employeeService.getById(lastId).getFirstName());
        Assert.assertEquals("UTestovich", employeeService.getById(lastId).getMiddleName());
        Assert.assertEquals("Updated-Tester", employeeService.getById(lastId).getLastName());
        Assert.assertEquals(LocalDate.parse("2000-02-02"), employeeService.getById(lastId).getBirthDate());
        Assert.assertEquals(3000, employeeService.getById(lastId).getSalary());
    }

    @Test
    public void test6_Delete() throws Exception {
        List<Employee> employees = employeeService.getAll();
        Assert.assertEquals(6, employees.size());       // Number of Employees should be 6
        Employee lastEmployee = employees.get(employees.size() - 1);
        employeeService.delete(lastEmployee.getId());               // Deleting last Employee
        employees = employeeService.getAll();
        Assert.assertEquals(5, employees.size());       // Number of Employees should be 5
    }
}
