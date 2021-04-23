package by.derevitsky.soap;

import by.derevitsky.EmployeeService;
import by.derevitsky.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.time.LocalDate;
import java.util.List;

@WebService(endpointInterface = "by.derevitsky.soap.EmployeeSoap")
@Service
public class EmployeeSoapImpl implements EmployeeSoap {

    // Stub
    private Employee stubEmployee = new Employee(1, 2,
            "Ivan", "Ivanych", "Ivanov",
            LocalDate.parse("2020-01-01"), 5000);

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Employee findEmployeeById(Integer id) {
        Employee foundEmployee = employeeService.getById(id);
        return foundEmployee;
    }

    @Override
    public Employee[] findAllEmployees() {
        List<Employee> foundEmployees = employeeService.getAll();
        return foundEmployees.toArray(new Employee[foundEmployees.size()]);
    }

    @Override
    public Employee[] findEmployeesByDepartmentId(Integer depId) {
        List<Employee> foundEmployees = employeeService.getByDepartmentId(depId);
        return foundEmployees.toArray(new Employee[foundEmployees.size()]);
    }

    @Override
    public void insertEmployee(Employee employee) {
        this.employeeService.insert(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        this.employeeService.update(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        this.employeeService.delete(id);
    }
}
