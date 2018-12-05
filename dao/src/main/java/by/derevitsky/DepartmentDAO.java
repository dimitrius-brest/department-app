package by.derevitsky;

import java.util.List;

/**
 * This interface describes CRUD methods for {@link Department}
 * @author Dmitry Derevitsky
 */
public interface DepartmentDAO {

    // Sets DataSource
    //void setDataSource(DataSource dataSource);

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
    void delete(int id);

}
