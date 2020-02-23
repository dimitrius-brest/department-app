package by.derevitsky.web;

import by.derevitsky.model.Department;
import by.derevitsky.web.model.DepartmentForView;

// --------- Junit 4 ---------
//import org.junit.Assert;
//import org.junit.Test;
// --------- Junit 5 ---------
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DepartmentsForViewTest {

    private Department department = new Department(5, "Test Department");

    @Test
    public void constructDepartmentForView() throws Exception {

        DepartmentForView departmentForView = new DepartmentForView(department, 500, true);

        assertEquals(5, departmentForView.getId());
        assertEquals("Test Department", departmentForView.getName());
        assertEquals(500, departmentForView.getAverageSalary());
        assertTrue(departmentForView.isHasEmployees());

        departmentForView.setAverageSalary(1000);
        departmentForView.setHasEmployees(false);
        assertEquals(1000, departmentForView.getAverageSalary());
        assertFalse(departmentForView.isHasEmployees());
    }
}
