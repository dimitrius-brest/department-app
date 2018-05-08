package by.derevitsky;

import java.util.List;

public interface EmployeeDAO {
    // CRUD operations

    // Insert
    void insertEmployee(Employee employee);
    // Read
    Employee getById(int id);
    // Update
    void updateEmployee(Employee employee);
    // Delete
    void deleteById(int id);

    // Get All
    List<Employee> getAll();
}
