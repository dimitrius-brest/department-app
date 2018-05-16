package by.derevitsky;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * This class implements DepartmentDAO interface for H2 database using Spring JDBC.
 * It contains a method to connect to database
 * and CRUD methods for its "Departments" table
 * @author Dmitry Derevitsky
 * @see DepartmentDAO
 */
public class DepartmentDAOImplH2 implements DepartmentDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    /**
     * Gets a bean of dataSource from Spring context
     * and creates jdbcTemplate with this dataSourse
     * @param dataSource
     */
    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Gets a list of all "Department" rows from database.
     * Uses auxiliary class DepartmentRowMapper.
     * @return the list of Departments
     * @see DepartmentRowMapper
     */
    @Override
    public List<Department> getAll() {
        String sql = "select * from departments";
        List<Department> departments = jdbcTemplate.query(sql, new DepartmentRowMapper());
        return departments;
    }

    /**
     * Gets a single "Department" from database by its "id".
     * Uses auxiliary class DepartmentRowMapper.
     * @param id the "id" to be searched by
     * @return the Department found by "id"
     * @see DepartmentRowMapper
     */
    @Override
    public Department getById(int id) {
        String sql = "select * from departments where id=?";
        Department department = (Department) jdbcTemplate.queryForObject(sql, new Object[]{id}, new DepartmentRowMapper());
        return department;
    }

    /**
     * Inserts a row of "Department" into database
     * @param name the name of the Department
     */
    @Override
    public void insert(String name) {
        String sql = "insert into departments (name) values (?)";
        jdbcTemplate.update(sql, name);
    }

    /**
     * Updates a row of "Department" by its "id"
     * @param id the id of the Department to be updated
     * @param name new name of the Department
     */
    @Override
    public void update(int id, String name) {
        String sql = "update departments set name=? where id=?";
        jdbcTemplate.update(sql, name, id);
    }

    /**
     * Deletes the row of Department with certain "id"
     * @param id the id of the Department to be deleted
     */
    @Override
    public void delete(int id) {
        String sql = "delete from departments where id=?";
        jdbcTemplate.update(sql, id);
    }
}
