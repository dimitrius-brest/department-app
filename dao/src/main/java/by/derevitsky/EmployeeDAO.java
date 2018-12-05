package by.derevitsky;

import java.util.List;

/**
 * This interface describes CRUD methods for {@link Employee}
 * @author Dmitry Derevitsky
 */
public interface EmployeeDAO {

    // Set DataSource
    //void setDataSource(DataSource dataSource);

    // ==== CRUD operations ====

    // Get All
    List<Employee> getAll();
    // Read
    Employee getById(int id);
    // Insert
    void insert(Employee employee);
    // Update
    void update(Employee employee);
    // Delete
    void delete(int id);

}
