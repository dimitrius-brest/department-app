package by.derevitsky.web;

import by.derevitsky.Department;
import by.derevitsky.web.model.DepartmentForView;
import org.junit.Assert;
import org.junit.Test;

public class DepartmentsForViewTest {

    private Department department = new Department(5, "Test Department");

    @Test
    public void constructDepartmentForView() throws Exception {

        DepartmentForView departmentForView = new DepartmentForView(department, 500, true);

        Assert.assertEquals(5, departmentForView.getId());
        Assert.assertEquals("Test Department", departmentForView.getName());
        Assert.assertEquals(500, departmentForView.getAverageSalary());
        Assert.assertTrue(departmentForView.isHasEmployees());

        departmentForView.setAverageSalary(1000);
        departmentForView.setHasEmployees(false);
        Assert.assertEquals(1000, departmentForView.getAverageSalary());
        Assert.assertFalse(departmentForView.isHasEmployees());
    }
}
