package by.derevitsky;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

/**
 * This class implements {@link EmployeeDAO} interface for H2 database using Spring JDBC.
 * It contains a method to connect to database
 * and CRUD methods for its "Employees" table
 * @author Dmitry Derevitsky
 * @see EmployeeDAO
 */
public class EmployeeDAOImplH2 implements EmployeeDAO {
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
     * Gets a list of all "Employee" rows from database.
     * Uses auxiliary class EmployeeRowMapper.
     * @return the list of Employees
     * @see EmployeeRowMapper
     */
    @Override
    public List<Employee> getAll() {
        String sql = "select * from employees";
        List<Employee> employees = jdbcTemplate.query(sql, new EmployeeRowMapper());
        return employees;
    }

    /**
     * Gets a single "Employee" from database by its "id".
     * Uses auxiliary class EmployeeRowMapper.
     * @param id the "id" to be searched by
     * @return the Employee found by "id"
     * @see EmployeeRowMapper
     */
    @Override
    public Employee getById(int id) {
        String sql = "select * from employees where id=?";
        Employee employee = (Employee) jdbcTemplate.queryForObject(sql, new Object[]{id}, new EmployeeRowMapper());
        return employee;
    }

    /**
     * Inserts a row of "Employee" into database
     * @param id_department the id of the Department
     * @param first_name first name of the Employee
     * @param middle_name middle name of the Employee
     * @param last_name last name of the Employee
     * @param birth_date date of birth of the Employee
     * @param salary salary of the Employee
     */
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

    /**
     * Updates a row of "Employee" by its "id"
     * @param id the id of the Employee to be updated
     * @param id_department new id of the Department
     * @param first_name new first name of the Employee
     * @param middle_name new middle name of the Employee
     * @param last_name new last name of the Employee
     * @param birth_date new date of birth of the Employee
     * @param salary new salary of the Employee
     */
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

    /**
     * Deletes the row of Employee with certain "id"
     * @param id the "id" of the Employee to be deleted
     */
    @Override
    public void delete(int id) {
        String sql = "delete from employees where id=?";
        jdbcTemplate.update(sql, id);
    }
}
