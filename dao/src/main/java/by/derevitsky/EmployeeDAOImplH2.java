package by.derevitsky;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;
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
    public void insert(int id_department,
                       String first_name, String middle_name, String last_name,
                       Date birth_date, int salary) {
        String sql = "insert into employees " +
                "(id_department, first_name, middle_name, last_name, birth_date, salary) " +
                "values (?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                id_department, first_name, middle_name, last_name, birth_date, salary);
    }

    @Override
    public void update(int id, int id_department,
                       String first_name, String middle_name, String last_name,
                       Date birth_date, int salary) {
        String sql = "update employees set " +
                "id_department=?, first_name=?, middle_name=?, last_name=?, birth_date=?, salary=? " +
                "where id=?";
        jdbcTemplate.update(sql,
                id_department, first_name, middle_name, last_name, birth_date, salary, id);
    }

    @Override
    public void delete(int id) {
        String sql = "delete from employees where id=?";
        jdbcTemplate.update(sql, id);
        System.out.println("Deleted record with id = " + id);
    }
}
