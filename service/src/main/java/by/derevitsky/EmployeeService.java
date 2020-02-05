package by.derevitsky;

import by.derevitsky.dao.EmployeeDAO;
import by.derevitsky.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    @Qualifier("h2EmployeeDAO")
    //@Qualifier("JpaEmployeeDAO")
    private EmployeeDAO employeeDAO;

    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    public Employee getById(Integer id) {
        return employeeDAO.getById(id);
    }

    public List<Employee> getByDepartmentId(Integer departmentId) {
        return employeeDAO.getByDepartmentId(departmentId);
    }

    public void insert(Employee employee) {employeeDAO.insert(employee);}

    public void update(Employee employee) {employeeDAO.update(employee);}

    public void delete(Integer id) {
        if (employeeDAO.getById(id) != null) {
            employeeDAO.delete(id);
        }
    }
}
