package by.derevitsky;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class EmployeeDAOImplH2 implements EmployeeDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Employee> getAll() {
        String sql = "select * from employees";
        List<Employee> employees = jdbcTemplate.query(sql, new EmployeeRowMapper());
        return employees;
    }

    @Override
    public Employee getById(int id) {
        String sql = "select * from employees where id=?";
        Employee employee = (Employee) jdbcTemplate.queryForObject(sql, new Object[]{id}, new EmployeeRowMapper());
        return employee;
    }

    @Override
    public void insert(Employee employee) {

    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(int id) {
        String sql = "delete from employees where id=?";
        jdbcTemplate.update(sql, id);
        System.out.println("Deleted record with id = " + id);
    }
}
