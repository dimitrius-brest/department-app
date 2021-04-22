package by.derevitsky.soap;

import by.derevitsky.model.Department;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface DepartmentSoap {

    @WebMethod
    Department[] findAllDepartments();

    @WebMethod
    Department findDepartmentById(Integer id);

    @WebMethod
    void insertDepartment(Department department);

    @WebMethod
    void updateDepartment(Department department);

    @WebMethod
    void deleteDepartment(Integer id);
}
