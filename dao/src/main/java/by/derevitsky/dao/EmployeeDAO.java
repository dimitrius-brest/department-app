package by.derevitsky.dao;

import by.derevitsky.model.Employee;

import java.util.List;

/**
 * This interface describes CRUD methods for {@link Employee}
 * @author Dmitry Derevitsky
 */
public interface EmployeeDAO {

    // ==== CRUD operations ====
    // Get All
    List<Employee> getAll();
    // Read
    Employee getById(int id);
    // Get by Department id
    List<Employee> getByDepartmentId(int departmentId);
    // Insert
    void insert(Employee employee);
    // Update
    void update(Employee employee);
    // Delete
    void delete(int id);

}
