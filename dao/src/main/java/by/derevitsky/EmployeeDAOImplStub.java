package by.derevitsky;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository("stubEmpDAO")
public class EmployeeDAOImplStub implements EmployeeDAO {
    public EmployeeDAOImplStub() throws ParseException {
    }

    /*@Override
    public void setDataSource(DataSource dataSource) {
    }*/

    List<Employee> employees = Arrays.asList(
            new Employee(1, 1, "John", "Ivanovich", "Bull",
                    LocalDate.parse("01-11-1974"), 10000),
            new Employee(2, 2, "Vladimir", "Ilyich", "Lenin",
                    LocalDate.parse("15-05-1980"), 10000)
    );

    @Override
    public List<Employee> getAll() {
        return employees;
    }

    @Override
    public Employee getById(int id) {
        return employees.get(id-1);
    }

    @Override
    public void insert(Employee employee) {
    }

    @Override
    public void update(Employee employee) {
    }

    @Override
    public void delete(int id) {

    }
}
