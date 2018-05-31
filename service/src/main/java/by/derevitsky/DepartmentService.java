package by.derevitsky;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class DepartmentService {

    ApplicationContext context = new ClassPathXmlApplicationContext("dao_context.xml");
    DepartmentDAO dao = (DepartmentDAO) context.getBean("departmentDAO");
    //private DepartmentDAO dao;

    public Department getById(Integer id) {
        return dao.getById(id);
    }

    public void save(Department department) {
        if (dao.getById(department.getId()) == null) {
            dao.insert(department.getName());
        } else {
            dao.update(department.getId(), department.getName());
        }
    }

    public void delete(Integer id) {
        if (dao.getById(id) != null) {
            dao.delete(id);
        }
    }

    public List<Department> getAll() {
        return dao.getAll();
    }
}
