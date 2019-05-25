package by.derevitsky;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ContextConfiguration("classpath:dao_context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentDaoCrudTest {

    @Autowired
    DepartmentDAO departmentDAO;

    @Test
    public void testGetAll() throws Exception {
        List<Department> departments = departmentDAO.getAll();
        Assert.assertNotNull(departments);                      // The list of Departments is not empty
        Assert.assertEquals(4, departments.size());     // The list of Departments contains 4 Departments
    }

    @Test
    public void testGetById() throws Exception {
        Assert.assertEquals("CEO", departmentDAO.getById(1).getName());
        Assert.assertEquals("Economists", departmentDAO.getById(2).getName());
        Assert.assertEquals("Programmers", departmentDAO.getById(3).getName());
        Assert.assertEquals("Dummies", departmentDAO.getById(4).getName());
    }

    @Test
    public void testInsert() throws Exception {
        departmentDAO.insert(new Department(0, "Test Department"));
        List<Department> departments = departmentDAO.getAll();
        Department lastDepartment = departments.get(departments.size() - 1);
        Assert.assertEquals("Test Department", departmentDAO.getById(lastDepartment.getId()).getName());
    }

    @Test
    public void testUpdate() throws Exception {
        List<Department> departments = departmentDAO.getAll();
        Department lastDepartment = departments.get(departments.size() - 1);
        lastDepartment.setName("Updated Test Department");
        departmentDAO.update(lastDepartment);
        Assert.assertEquals("Updated Test Department", departmentDAO.getById(lastDepartment.getId()).getName());
    }
}
