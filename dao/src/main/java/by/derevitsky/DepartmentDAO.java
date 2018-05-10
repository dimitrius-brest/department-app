package by.derevitsky;

import javax.sql.DataSource;
import java.util.List;

public interface DepartmentDAO {

    // test method
    void testMethod(String testString);

    // Set DataSource
    void setDataSource(DataSource dataSource);

    // ==== CRUD operations ====
    // Insert
    void insertDepartment(Department department);
    // Read
    Department getById(int id);
    // Update
    void updateDepartment(Department department);
    // Delete
    void deleteById(int id);

    // Get All
    List<Department> getAll();
}
