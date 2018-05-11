package by.derevitsky;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class DepartmentDAOImplH2 implements DepartmentDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Department> getAll() {
        String sql = "select * from departments";
        List<Department> departments = jdbcTemplate.query(sql, new DepartmentRowMapper());
        return departments;
    }

    @Override
    public Department getById(int id) {
        return null;
    }

    @Override
    public void insert(Department department) {
    }

    @Override
    public void update(Department department) {
    }

    @Override
    public void delete(Department department) {
    }
}
