package by.derevitsky;

import by.derevitsky.dao.DepartmentDAO;
import by.derevitsky.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    @Qualifier("h2DepDAO")
    private DepartmentDAO departmentDAO;

    public List<Department> getAll() {
        return departmentDAO.getAll();
    }

    public Department getById(Integer id) {
        return departmentDAO.getById(id);
    }

    public void insert(Department department) { departmentDAO.insert(department);}

    public void update(Department department) { departmentDAO.update(department);}

    public void delete(Integer id) {
        if (departmentDAO.getById(id) != null) {
            departmentDAO.delete(id);
        }
    }
}
