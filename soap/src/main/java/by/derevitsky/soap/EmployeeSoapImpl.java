package by.derevitsky.soap;

import by.derevitsky.model.Employee;

import javax.jws.WebService;

@WebService(endpointInterface = "by.derevitsky.soap.EmployeeSoap")
public class EmployeeSoapImpl implements EmployeeSoap {
    @Override
    public Employee[] getAllEmployees() {
        return new Employee[0];
    }

//    @Override
//    public Employee getEmployeeById(Integer id) {
//        return null;
//    }
//
//    @Override
//    public void insertEmployee(Employee employee) {
//    }
//
//    @Override
//    public void updateEmployee(Employee employee) {
//    }

    @Override
    public void deleteEmployee(Integer id) {

    }
}
