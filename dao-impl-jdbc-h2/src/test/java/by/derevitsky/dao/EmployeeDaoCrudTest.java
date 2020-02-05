package by.derevitsky.dao;

import by.derevitsky.dao.EmployeeDAO;
import by.derevitsky.model.Employee;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

@ContextConfiguration("classpath:dao_context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "jdbc-h2")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoCrudTest {

    @Autowired @Qualifier("h2EmployeeDAO")
    EmployeeDAO employeeDAO;

    @Test
    public void test1_GetAll() throws Exception {
        List<Employee> employees = employeeDAO.getAll();
        Assert.assertNotNull(employees);                    // The list of Employees is not empty
        Assert.assertEquals(5, employees.size());   // The list of Employees contains 4 Departments
    }

    @Test
    public void test2_GetById() throws Exception {
        Employee employee = employeeDAO.getById(1);
        Assert.assertEquals(1, employee.getIdDepartment());
        Assert.assertEquals("Ivan", employee.getFirstName());
        Assert.assertEquals("Ivanovich", employee.getMiddleName());
        Assert.assertEquals("Ivanov", employee.getLastName());
        Assert.assertEquals(LocalDate.parse("1970-01-01"), employee.getBirthDate());
        Assert.assertEquals(10000, employee.getSalary());
    }

    @Test
    public void test3_GetByDepartmentId() throws Exception {
        List<Employee> employeesInDepartment = employeeDAO.getByDepartmentId(1); // Employees in Department with id=1
        Assert.assertEquals(1, employeesInDepartment.size());
        employeesInDepartment = employeeDAO.getByDepartmentId(2);               // Employees in Department with id=2
        Assert.assertEquals(2, employeesInDepartment.size());
        employeesInDepartment = employeeDAO.getByDepartmentId(4);               // Employees in Department with id=1
        Assert.assertEquals(0, employeesInDepartment.size());
    }

    @Test
    public void test4_Insert() throws Exception {
        employeeDAO.insert(new Employee(0, 4, "Test", "Testovich", "Tester",
                LocalDate.parse("1900-01-01"), 500));
        List<Employee> employees = employeeDAO.getAll();
        Employee lastEmployee = employees.get(employees.size() - 1);   // Last Employee after inserting
        int lastId = lastEmployee.getId();
        Assert.assertEquals(4, employeeDAO.getById(lastId).getIdDepartment());
        Assert.assertEquals("Test", employeeDAO.getById(lastId).getFirstName());
        Assert.assertEquals("Testovich", employeeDAO.getById(lastId).getMiddleName());
        Assert.assertEquals("Tester", employeeDAO.getById(lastId).getLastName());
        Assert.assertEquals(LocalDate.parse("1900-01-01"), employeeDAO.getById(lastId).getBirthDate());
        Assert.assertEquals(500, employeeDAO.getById(lastId).getSalary());
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
        Assert.assertEquals(1, employeeDAO.getById(lastId).getIdDepartment());
        Assert.assertEquals("UTest", employeeDAO.getById(lastId).getFirstName());
        Assert.assertEquals("UTestovich", employeeDAO.getById(lastId).getMiddleName());
        Assert.assertEquals("Updated-Tester", employeeDAO.getById(lastId).getLastName());
        Assert.assertEquals(LocalDate.parse("2000-02-02"), employeeDAO.getById(lastId).getBirthDate());
        Assert.assertEquals(3000, employeeDAO.getById(lastId).getSalary());
    }

    @Test
    public void test6_Delete() throws Exception {
        List<Employee> employees = employeeDAO.getAll();
        Employee lastEmployee = employees.get(employees.size() - 1);
        Assert.assertEquals(6, employees.size());       // Number of Employees should be 6
        employeeDAO.delete(lastEmployee.getId());               // Deleting last Employee
        employees = employeeDAO.getAll();
        Assert.assertEquals(5, employees.size());       // Number of Employees should be 5
    }
}
