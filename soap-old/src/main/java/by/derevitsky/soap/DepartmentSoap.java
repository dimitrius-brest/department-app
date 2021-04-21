package by.derevitsky.soap;

import by.derevitsky.model.Department;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface DepartmentSoap {

    @WebMethod
    Department[] getAllDepartments();

    @WebMethod
    Department getDepartmentById(Integer id);

    @WebMethod
    void insertDepartment(Department department);

    @WebMethod
    void updateDepartment(Department department);

    @WebMethod
    void deleteDepartment(Integer id);
}
