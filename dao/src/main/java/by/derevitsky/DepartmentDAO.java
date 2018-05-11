package by.derevitsky;

import javax.sql.DataSource;
import java.util.List;

public interface DepartmentDAO {

    // Set DataSource
    void setDataSource(DataSource dataSource);

    // ==== CRUD operations ====

    // Get All
    List<Department> getAll();
    // Read
    Department getById(int id);

    // Insert
    void insert(Department department);
    // Update
    void update(Department department);
    // Delete
    void delete(Department department);


}
