package by.derevitsky;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Auxiliary class for usage in the {@link EmployeeDAOImplH2} class methods
 * and other implementations of EmployeeDAO interface
 * @author Dmitry Derevitsky
 * @see EmployeeDAOImplH2#getAll()
 * @see EmployeeDAOImplH2#getById(int)
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
