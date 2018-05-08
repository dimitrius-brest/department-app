package by.derevitsky;

import java.util.List;

public interface DepartmentDAO {
    // CRUD operations

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
