package by.derevitsky;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

@ContextConfiguration("classpath:dao_context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeDaoCrudTest {

    @Autowired
    EmployeeDAO employeeDAO;

    @Test
    public void testGetAll(){
        List<Employee> employees = employeeDAO.getAll();
        Assert.assertNotNull(employees);                    // The list of Employees is not empty
        Assert.assertEquals(5, employees.size());   // The list of Employees contains 4 Departments
    }

    @Test
    public void testGetById(){
        Employee employee = employeeDAO.getById(1);
        Assert.assertEquals(1, employee.getIdDepartment());
        Assert.assertEquals("Ivan", employee.getFirstName());
        Assert.assertEquals("Ivanovich", employee.getMiddleName());
        Assert.assertEquals("Ivanov", employee.getLastName());
        Assert.assertEquals(LocalDate.parse("1970-01-01"), employee.getBirthDate());
        Assert.assertEquals(10000, employee.getSalary());
    }
}
