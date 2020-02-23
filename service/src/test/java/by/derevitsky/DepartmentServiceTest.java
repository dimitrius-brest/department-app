package by.derevitsky;

import by.derevitsky.model.Department;

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

// ---------  ---------
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;          // for junit 5
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;      // for junit 4

import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)       // junit 4
@ExtendWith(SpringExtension.class)              // junit 5
@ContextConfiguration("classpath:dao_context.xml")
@ActiveProfiles(profiles = {"jdbc", "h2mem"})
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)     // junit 4
@TestMethodOrder(MethodOrderer.Alphanumeric.class)  // junit 5
public class DepartmentServiceTest {

    @Autowired
    DepartmentService departmentService;

    @Test
    public void test1_GetAll() throws Exception {
        List<Department> departments = departmentService.getAll();
        assertNotNull(departments);                      // The list of Departments is not empty
        assertEquals(4, departments.size());     // The list of Departments contains 4 Departments
    }

    @Test
    public void test2_GetById() throws Exception {
        assertEquals("CEO-Mem", departmentService.getById(1).getName());         // Department id=1
        assertEquals("Economists-Mem", departmentService.getById(2).getName());  // Department id=1
        assertEquals("Programmers-Mem", departmentService.getById(3).getName()); // Department id=1
        assertEquals("Dummies-Mem", departmentService.getById(4).getName());     // Department id=1
    }

    @Test
    public void test3_Insert() throws Exception {
        departmentService.insert(new Department(0, "Test Department"));
        List<Department> departments = departmentService.getAll();
        Department lastDepartment = departments.get(departments.size() - 1);        // Last Department after inserting
        assertEquals("Test Department", departmentService.getById(lastDepartment.getId()).getName());
    }

    @Test
    public void test4_Update() throws Exception {
        List<Department> departments = departmentService.getAll();
        Department lastDepartment = departments.get(departments.size() - 1);
        lastDepartment.setName("Updated Test Department");
        departmentService.update(lastDepartment);                           // Updating last Department
        assertEquals("Updated Test Department", departmentService.getById(lastDepartment.getId()).getName());
    }

    @Test
    public void test5_Delete() throws Exception {
        List<Department> departments = departmentService.getAll();
        assertEquals(5, departments.size());        // The updated list of Departments contains 5 Departments
        Department lastDepartment = departments.get(departments.size() - 1);
        assertEquals("Updated Test Department", departmentService.getById(lastDepartment.getId()).getName());
        departmentService.delete(lastDepartment.getId());          // Deleting last Department
        departments = departmentService.getAll();
        assertEquals(4, departments.size());        // Now the list of Departments contains 4 Departments
        lastDepartment = departments.get(departments.size() - 1);
        assertEquals("Dummies-Mem", departmentService.getById(lastDepartment.getId()).getName());
    }
}
