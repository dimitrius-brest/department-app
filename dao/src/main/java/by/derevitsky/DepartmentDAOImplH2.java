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
        String sql = "select * from departments where id=?";
        Department department = (Department) jdbcTemplate.queryForObject(sql, new Object[]{id}, new DepartmentRowMapper());
        return department;
    }

    @Override
    public void insert(String name) {
        String sql = "insert into departments (name) values (?)";
        jdbcTemplate.update(sql, name);
    }

    @Override
    public void update(int id, String name) {
        String sql = "update departments set name=? where id=?";
        jdbcTemplate.update(sql, name, id);
    }

    @Override
    public void delete(int id) {
        String sql = "delete from departments where id=?";
        jdbcTemplate.update(sql, id);
        System.out.println("Deleted record with id = " + id);
    }
}
