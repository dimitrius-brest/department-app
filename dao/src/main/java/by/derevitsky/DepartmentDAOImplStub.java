package by.derevitsky;

import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@Repository("stubDepDAO")
public class DepartmentDAOImplStub implements DepartmentDAO {

    private List<Department> departments = Arrays.asList(
            new Department(1, "CEO"),
            new Department(2, "Economists"),
            new Department(3, "Programmers"),
            new Department(4, "Marketing") );

    @Override
    public List<Department> getAll() {
        return departments;
    }

    @Override
    public Department getById(int id) {
        return departments.get(id-1);
    }

    @Override
    public void insert(Department department) {
    }

    @Override
    public void update(Department department) {
    }

    @Override
    public void delete(int id) {
    }
}
