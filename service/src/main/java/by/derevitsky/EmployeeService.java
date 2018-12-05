package by.derevitsky;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeDAO dao;

    public Employee getById(Integer id) {
        return dao.getById(id);
    }

    public void save(Employee employee) {
        if (dao.getById(employee.getId()) == null) {
            dao.insert(employee);
        } else {
            dao.update(employee);
        }
    }

    public void delete(Integer id) {
        if (dao.getById(id) != null) {
            dao.delete(id);
        }
    }

    public List<Employee> getAll() {
        return dao.getAll();
    }
}
