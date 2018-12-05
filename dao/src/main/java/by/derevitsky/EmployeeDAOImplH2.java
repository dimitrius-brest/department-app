package by.derevitsky;

import org.springframework.beans.factory.annotation.Autowired;
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
    //private DataSource dataSource;
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
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }*/

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
     * @param employee the Employee to be inserted
     */
    @Override
    public void insert(Employee employee) {
        String sql = "insert into employees " +
                "(id_department, first_name, middle_name, last_name, birth_date, salary) " +
                "values (?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                employee.getIdDepartment(),
                employee.getFirstName(), employee.getMiddleName(), employee.getLastName(),
                employee.getBirthDate(), employee.getSalary());

    }

    /**
     * Updates a row of "Employee" by its "id"
     * @param employee the Employee to be updated
     */
    @Override
    public void update(Employee employee) {
        String sql = "update employees set " +
                "id_department=?, first_name=?, middle_name=?, last_name=?, birth_date=?, salary=? " +
                "where id=?";
        jdbcTemplate.update(sql,
                employee.getIdDepartment(),
                employee.getFirstName(), employee.getMiddleName(), employee.getLastName(),
                employee.getBirthDate(), employee.getSalary(), employee.getId());
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
