package by.derevitsky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * This class implements {@link DepartmentDAO} interface for H2 database using Spring JDBC.
 * It contains a method to connect to database
 * and CRUD methods for its "Departments" table
 * @author Dmitry Derevitsky
 * @see DepartmentDAO
 */
@Repository("h2DepDAO")
public class DepartmentDAOImplH2 implements DepartmentDAO {

//    private DataSource dataSource;

    //@Autowired
    private JdbcTemplate jdbcTemplate;

    /*
     * Gets a bean of dataSource from Spring context
     * and creates jdbcTemplate with this dataSourse
     * @param dataSource
     */
    /*@Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);*/

        // --- Populating in-memory database
/*        String create_table =
          "create table departments (id INT(10) auto_increment, name VARCHAR(100) not null, PRIMARY KEY (id))";
        String populate_table_1 =
          "insert into departments (name) values ('Super Department')";
        String populate_table_2 =
                "insert into departments (name) values ('Usual Department')";

        this.jdbcTemplate.execute(create_table);
        this.jdbcTemplate.execute(populate_table_1);
        this.jdbcTemplate.execute(populate_table_2);*/

    /*}*/

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
     * @param department the Department to be added
     */
    @Override
    public void insert(Department department) {
        String sql = "insert into departments (name) values (?)";
        jdbcTemplate.update(sql, department.getName());
    }

    /**
     * Updates a row of "Department" by its "id"
     * @param department the Department to be updated
     */
    @Override
    public void update(Department department) {
        String sql = "update departments set name=? where id=?";
        jdbcTemplate.update(sql, department.getName(), department.getId());
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
