package by.derevitsky;

import javax.sql.DataSource;
import java.util.Date;
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
    void insert(int id_department,
                String first_name, String middle_name, String last_name,
                Date birth_date, int salary);
    // Update
    void update(int id, int id_department,
                String first_name, String middle_name, String last_name,
                Date birth_date, int salary);
    // Delete
    void delete(int id);

}
