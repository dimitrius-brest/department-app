package by.derevitsky.soap;

import by.derevitsky.EmployeeService;
import by.derevitsky.model.Employee;
import by.derevitsky.soap.model.EmployeeForSoap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "by.derevitsky.soap.EmployeeSoap")
@Service
public class EmployeeSoapImpl implements EmployeeSoap {

    // Stub
//    private Employee stubEmployee = new Employee(1, 2,
//            "Ivan", "Ivanych", "Ivanov",
//            LocalDate.parse("2020-01-01"), 5000);

    @Autowired
    private EmployeeService employeeService;

    @Override
    public EmployeeForSoap findEmployeeById(Integer id) {
        Employee foundEmployee = employeeService.getById(id);
        return new EmployeeForSoap(foundEmployee);
    }

    @Override
    public EmployeeForSoap[] findAllEmployees() {
        List<Employee> foundEmployees = employeeService.getAll();
        List<EmployeeForSoap> employeeForSoaps = new ArrayList<>();
        for(Employee emp : foundEmployees) {
            employeeForSoaps.add(new EmployeeForSoap(emp));
        }
        return employeeForSoaps.toArray(new EmployeeForSoap[employeeForSoaps.size()]);
    }

    @Override
    public EmployeeForSoap[] findEmployeesByDepartmentId(Integer depId) {
        List<Employee> foundEmployees = employeeService.getByDepartmentId(depId);
        List<EmployeeForSoap> employeeForSoaps = new ArrayList<>();
        for(Employee emp : foundEmployees) {
            employeeForSoaps.add(new EmployeeForSoap(emp));
        }
        return employeeForSoaps.toArray(new EmployeeForSoap[employeeForSoaps.size()]);
    }

    @Override
    public void insertEmployee(EmployeeForSoap emp) {
        this.employeeService.insert(emp.convertToEmployee());
    }

    @Override
    public void updateEmployee(EmployeeForSoap emp) {
        this.employeeService.update(emp.convertToEmployee());
    }

    @Override
    public void deleteEmployee(Integer id) {
        this.employeeService.delete(id);
    }
}
