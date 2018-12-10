package by.derevitsky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    @Qualifier("h2EmpDAO")
    private EmployeeDAO employeeDAO;

    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    public Employee getById(Integer id) {
        return employeeDAO.getById(id);
    }

    public void save(Employee employee) {
        if (employeeDAO.getById(employee.getId()) == null) {
            employeeDAO.insert(employee);
        } else {
            employeeDAO.update(employee);
        }
    }

    public void delete(Integer id) {
        if (employeeDAO.getById(id) != null) {
            employeeDAO.delete(id);
        }
    }
}
