package by.derevitsky;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class DepartmentDAOImplH2 implements DepartmentDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void testMethod(String testString) {
        System.out.println("Тестовая строка из DepartmentDAO: " + testString);
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertDepartment(Department department) {
    }

    public Department getById(int id) {
        return null;
    }

    public void updateDepartment(Department department) {
    }

    public void deleteById(int id) {
    }

    public List<Department> getAll() {
        String sql = "select * from departments";
        List<Department> departments = jdbcTemplate.query(sql, new DepartmentRowMapper());
        return departments;
    }
}
