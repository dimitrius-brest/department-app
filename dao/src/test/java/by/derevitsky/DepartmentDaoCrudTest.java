package by.derevitsky;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ContextConfiguration("classpath:dao_context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoCrudTest {

    @Autowired
    DepartmentDAO departmentDAO;

    @Test
    public void test1_GetAll() throws Exception {
        List<Department> departments = departmentDAO.getAll();
        Assert.assertNotNull(departments);                      // The list of Departments is not empty
        Assert.assertEquals(4, departments.size());     // The list of Departments contains 4 Departments
    }

    @Test
    public void test2_GetById() throws Exception {
        Assert.assertEquals("CEO", departmentDAO.getById(1).getName());           // Department id=1
        Assert.assertEquals("Economists", departmentDAO.getById(2).getName());    // Department id=2
        Assert.assertEquals("Programmers", departmentDAO.getById(3).getName());   // Department id=3
        Assert.assertEquals("Dummies", departmentDAO.getById(4).getName());       // Department id=4
    }

    @Test
    public void test3_Insert() throws Exception {
        departmentDAO.insert(new Department(0, "Test Department"));
        List<Department> departments = departmentDAO.getAll();
        Department lastDepartment = departments.get(departments.size() - 1);     // Last Department after inserting
        Assert.assertEquals("Test Department", departmentDAO.getById(lastDepartment.getId()).getName());
    }

    @Test
    public void test4_Update() throws Exception {
        List<Department> departments = departmentDAO.getAll();
        Department lastDepartment = departments.get(departments.size() - 1);
        lastDepartment.setName("Updated Test Department");
        departmentDAO.update(lastDepartment);                                    // Updating last Department
        Assert.assertEquals("Updated Test Department", departmentDAO.getById(lastDepartment.getId()).getName());
    }

    @Test
    public void test5_Delete() throws Exception {
        List<Department> departments = departmentDAO.getAll();
        Assert.assertEquals(5, departments.size());     // The updated list of Departments contains 5 Departments
        Department lastDepartment = departments.get(departments.size() - 1);
        Assert.assertEquals("Updated Test Department", departmentDAO.getById(lastDepartment.getId()).getName());
        departmentDAO.delete(lastDepartment.getId());           // Deleting last Department
        departments = departmentDAO.getAll();
        Assert.assertEquals(4, departments.size());     // Now the list of Departments contains 4 Departments
        lastDepartment = departments.get(departments.size() - 1);
        Assert.assertNotEquals("Updated Test Department", departmentDAO.getById(lastDepartment.getId()).getName());
        Assert.assertEquals("Dummies", departmentDAO.getById(lastDepartment.getId()).getName());
    }
}
