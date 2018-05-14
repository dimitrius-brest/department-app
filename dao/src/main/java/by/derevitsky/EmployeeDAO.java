package by.derevitsky;

import javax.sql.DataSource;
import java.util.List;

public interface EmployeeDAO {

    // Set DataSource
    void setDataSource(DataSource dataSource);

    // CRUD operations

    // Get All
    List<Employee> getAll();
    // Read
    Employee getById(int id);

    // Insert
    void insert(Employee employee);
    // Update
    void update(int id);
    // Delete
    void delete(int id);

}
