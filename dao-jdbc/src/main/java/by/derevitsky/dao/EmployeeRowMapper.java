package by.derevitsky.dao;

import by.derevitsky.model.Employee;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Auxiliary class for usage in the {@link EmployeeDAOJdbc} class methods
 * and other implementations of EmployeeDAO interface
 * @author Dmitry Derevitsky
 * @see EmployeeDAOJdbc#getAll()
 * @see EmployeeDAOJdbc#getById(int)
 * @see EmployeeDAO
 */
public class EmployeeRowMapper implements RowMapper  {
    @Nullable
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setIdDepartment((resultSet.getInt("id_department")));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setMiddleName(resultSet.getString("middle_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
        employee.setSalary(resultSet.getInt("salary"));
        return employee;
    }
}
