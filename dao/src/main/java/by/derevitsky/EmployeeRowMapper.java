package by.derevitsky;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper  {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setId_department(resultSet.getInt("id_department"));
        employee.setFirst_name(resultSet.getString("first_name"));
        employee.setMiddle_name(resultSet.getString("middle_name"));
        employee.setLast_name(resultSet.getString("last_name"));
        employee.setBirth_date(resultSet.getDate("birth_date"));
        employee.setSalary(resultSet.getInt("salary"));
        return employee;
    }
}
