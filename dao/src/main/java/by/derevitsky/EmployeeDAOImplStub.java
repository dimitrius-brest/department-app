package by.derevitsky;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Repository("stubEmpDAO")
public class EmployeeDAOImplStub implements EmployeeDAO {

    private List<Employee> employees = Arrays.asList(
            new Employee(1, 1, "John", "Ivanovich", "Bull",
                    LocalDate.parse("1974-11-01"), 10000),
            new Employee(2, 2, "Vladimir", "Ilyich", "Lenin",
                    LocalDate.parse("1980-05-15"), 10000)
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
