package by.derevitsky.soap;

import by.derevitsky.model.Employee;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface EmployeeSoap {

    @WebMethod
    Employee findEmployeeById(Integer id);

    @WebMethod
    Employee[] findAllEmployees();

    @WebMethod
    Employee[] findEmployeesByDepartmentId(Integer depId);

    @WebMethod
    void insertEmployee(Employee employee);

    @WebMethod
    void updateEmployee(Employee employee);

    @WebMethod
    void deleteEmployee(Integer id);
}
