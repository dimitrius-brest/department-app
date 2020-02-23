package by.derevitsky.dao;

import by.derevitsky.dao.DepartmentDAO;
import by.derevitsky.model.Department;

// --------- Junit 4 ---------
//import org.junit.Assert;
//import org.junit.Ignore;
//import org.junit.Test;
// import org.junit.FixMethodOrder;
// import org.junit.runner.RunWith;
// import org.junit.runners.MethodSorters;

// --------- Junit 5 ---------
//import org.junit.jupiter.api.Disabled;
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
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;      // for junit 4
import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)       // junit 4
@ExtendWith(SpringExtension.class)              // junit 5
//@ContextConfiguration("classpath:dao_context.xml")
@ContextConfiguration(classes = ConfigDAOJdbc.class)
@ActiveProfiles(profiles = {"jdbc", "h2mem"})
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)     // junit 4
@TestMethodOrder(MethodOrderer.Alphanumeric.class)  // junit 5
public class DepartmentDaoCrudTest {

    @Autowired @Qualifier("JdbcDepartmentDAO")
    DepartmentDAO departmentDAO;

    @Test
    public void test1_GetAll() throws Exception {
        List<Department> departments = departmentDAO.getAll();
        //assertNotNull(departments);                      // The list of Departments is not empty
        assertNotNull(departments);
        assertEquals(4, departments.size());     // The list of Departments contains 4 Departments
    }

    //@Ignore       // junit 4
    //@Disabled     // junit 5
    @Test
    public void test2_GetById() throws Exception {
        assertEquals("CEO-Mem", departmentDAO.getById(1).getName());           // Department id=1
        assertEquals("Economists-Mem", departmentDAO.getById(2).getName());    // Department id=2
        assertEquals("Programmers-Mem", departmentDAO.getById(3).getName());   // Department id=3
        assertEquals("Dummies-Mem", departmentDAO.getById(4).getName());       // Department id=4
    }

    @Test
    public void test3_Insert() throws Exception {
        departmentDAO.insert(new Department(0, "Test Department"));
        List<Department> departments = departmentDAO.getAll();
        Department lastDepartment = departments.get(departments.size() - 1);     // Last Department after inserting
        assertEquals("Test Department", departmentDAO.getById(lastDepartment.getId()).getName());
    }

    @Test
    public void test4_Update() throws Exception {
        List<Department> departments = departmentDAO.getAll();
        Department lastDepartment = departments.get(departments.size() - 1);
        lastDepartment.setName("Updated Test Department");
        departmentDAO.update(lastDepartment);                                    // Updating last Department
        assertEquals("Updated Test Department", departmentDAO.getById(lastDepartment.getId()).getName());
    }

    //@Ignore       // junit 4
    //@Disabled     // junit 5
    @Test
    public void test5_Delete() throws Exception {
        List<Department> departments = departmentDAO.getAll();
        assertEquals(5, departments.size());     // The updated list of Departments contains 5 Departments
        Department lastDepartment = departments.get(departments.size() - 1);
        assertEquals("Updated Test Department", departmentDAO.getById(lastDepartment.getId()).getName());
        departmentDAO.delete(lastDepartment.getId());           // Deleting last Department
        departments = departmentDAO.getAll();
        assertEquals(4, departments.size());     // Now the list of Departments contains 4 Departments
        lastDepartment = departments.get(departments.size() - 1);
        assertNotEquals("Updated Test Department", departmentDAO.getById(lastDepartment.getId()).getName());
        assertEquals("Dummies-Mem", departmentDAO.getById(lastDepartment.getId()).getName());
    }
}
