package by.derevitsky.dao;

import by.derevitsky.model.Department;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Auxiliary class for usage in the {@link DepartmentDAOJdbc} class methods
 * and other implementations of DepartmentDAO interface
 * @author Dmitry Derevitsky
 * @see DepartmentDAOJdbc#getAll()
 * @see DepartmentDAOJdbc#getById(int)
 * @see DepartmentDAO
 */
public class DepartmentRowMapper implements RowMapper {
    @Nullable
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("id"));
        department.setName(resultSet.getString("name"));
        return department;
    }
}
