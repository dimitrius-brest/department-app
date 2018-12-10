package by.derevitsky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

    public void save(Department department) {
        if (departmentDAO.getById(department.getId()) == null) {
            departmentDAO.insert(department);
        } else {
            departmentDAO.update(department);
        }
    }

    public void delete(Integer id) {
        if (departmentDAO.getById(id) != null) {
            departmentDAO.delete(id);
        }
    }
}
