package by.derevitsky;

import javax.sql.DataSource;
import java.util.List;

/**
 * This interface describes CRUD methods for "Department"
 * @author Dmitry Derevitsky
 */
public interface DepartmentDAO {

    // Sets DataSource
    void setDataSource(DataSource dataSource);

    // ==== CRUD operations ====

    // Get All
    List<Department> getAll();
    // Read
    Department getById(int id);
    // Insert
    void insert(String name);
    // Update
    void update(int id, String name);
    // Delete
    void delete(int id);

}
