package by.derevitsky;

import by.derevitsky.model.Department;
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

import java.util.List;

@Ignore("Temporary...")
@ContextConfiguration("classpath:dao_context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = {"jdbc", "h2mem"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentServiceTest {

    @Autowired
    DepartmentService departmentService;

    @Test
    public void test1_GetAll() throws Exception {
        List<Department> departments = departmentService.getAll();
        Assert.assertNotNull(departments);                      // The list of Departments is not empty
        Assert.assertEquals(4, departments.size());     // The list of Departments contains 4 Departments
    }

    @Test
    public void test2_GetById() throws Exception {
        Assert.assertEquals("CEO", departmentService.getById(1).getName());         // Department id=1
        Assert.assertEquals("Economists", departmentService.getById(2).getName());  // Department id=1
        Assert.assertEquals("Programmers", departmentService.getById(3).getName()); // Department id=1
        Assert.assertEquals("Dummies", departmentService.getById(4).getName());     // Department id=1
    }

    @Test
    public void test3_Insert() throws Exception {
        departmentService.insert(new Department(0, "Test Department"));
        List<Department> departments = departmentService.getAll();
        Department lastDepartment = departments.get(departments.size() - 1);        // Last Department after inserting
        Assert.assertEquals("Test Department", departmentService.getById(lastDepartment.getId()).getName());
    }

    @Test
    public void test4_Update() throws Exception {
        List<Department> departments = departmentService.getAll();
        Department lastDepartment = departments.get(departments.size() - 1);
        lastDepartment.setName("Updated Test Department");
        departmentService.update(lastDepartment);                           // Updating last Department
        Assert.assertEquals("Updated Test Department", departmentService.getById(lastDepartment.getId()).getName());
    }

    @Test
    public void test5_Delete() throws Exception {
        List<Department> departments = departmentService.getAll();
        Assert.assertEquals(5, departments.size());        // The updated list of Departments contains 5 Departments
        Department lastDepartment = departments.get(departments.size() - 1);
        Assert.assertEquals("Updated Test Department", departmentService.getById(lastDepartment.getId()).getName());
        departmentService.delete(lastDepartment.getId());          // Deleting last Department
        departments = departmentService.getAll();
        Assert.assertEquals(4, departments.size());        // Now the list of Departments contains 4 Departments
        lastDepartment = departments.get(departments.size() - 1);
        Assert.assertEquals("Dummies", departmentService.getById(lastDepartment.getId()).getName());
    }
}
