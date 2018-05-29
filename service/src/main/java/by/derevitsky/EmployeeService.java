package by.derevitsky;

import java.util.List;

public class EmployeeService {

    private EmployeeDAO dao;

    public Employee getById(Integer id) {
        return dao.getById(id);
    }

    public void save(Employee employee) {
        if (dao.getById(employee.getId()) == null) {
            dao.insert(employee.getId_department(),
                       employee.getFirst_name(),
                       employee.getMiddle_name(),
                       employee.getLast_name(),
                       employee.getBirth_date(),
                       employee.getSalary());
        } else {
            dao.update(employee.getId(),
                    employee.getId_department(),
                    employee.getFirst_name(),
                    employee.getMiddle_name(),
                    employee.getLast_name(),
                    employee.getBirth_date(),
                    employee.getSalary());
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
