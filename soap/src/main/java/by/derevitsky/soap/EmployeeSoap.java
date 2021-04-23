package by.derevitsky.soap;

import by.derevitsky.soap.model.EmployeeForSoap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface EmployeeSoap {

    @WebMethod
    EmployeeForSoap findEmployeeById(Integer id);

    @WebMethod
    EmployeeForSoap[] findAllEmployees();

    @WebMethod
    EmployeeForSoap[] findEmployeesByDepartmentId(Integer depId);

    @WebMethod
    void insertEmployee(EmployeeForSoap employee);

    @WebMethod
    void updateEmployee(EmployeeForSoap employee);

    @WebMethod
    void deleteEmployee(Integer id);
}
