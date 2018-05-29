package by.derevitsky;

import java.util.List;

public class DepartmentService {

    private DepartmentDAO dao;

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
