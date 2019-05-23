package by.derevitsky;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration("classpath:dao_context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentDaoCrudTest {

    @Autowired
    DepartmentDAO departmentDAO;

    @Test
    public void testGetAll() throws Exception {
        List<Department> departments = departmentDAO.getAll();
        Assert.assertNotNull(departments);                      // The list is not empty
        Assert.assertEquals(4, departments.size());     // The list contains 4 Departments
    }
}
