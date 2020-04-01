package by.derevitsky.soap;

import by.derevitsky.model.Employee;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface EmployeeSoap {

    @WebMethod
    Employee[] getAllEmployees();

//    @WebMethod
//    Employee getEmployeeById(Integer id);
//
//    @WebMethod
//    void insertEmployee(Employee employee);
//
//    @WebMethod
//    void updateEmployee(Employee employee);

    @WebMethod
    void deleteEmployee(Integer id);
}
