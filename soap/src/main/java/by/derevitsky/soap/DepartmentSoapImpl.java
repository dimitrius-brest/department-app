package by.derevitsky.soap;

//import by.derevitsky.DepartmentService;
import by.derevitsky.model.Department;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.jws.WebService;
import java.util.List;

//@Component
@WebService(endpointInterface = "by.derevitsky.soap.DepartmentSoap")
public class DepartmentSoapImpl /*extends SpringBeanAutowiringSupport*/ implements DepartmentSoap {

    //@Autowired
    //private DepartmentService departmentService;

//    @Override
//    public List<Department> getAllDepartments() {
//        return null;
//        //return this.departmentService.getAll();
//    }
//
    @Override
    public Department getDepartmentById(Integer id) {
        Department department = new Department(id, "Stub Dep.");
        return department;
        //return this.departmentService.getById(id);
    }
//
//    @Override
//    public void insertDepartment(Department department) {
//        //this.departmentService.insert(department);
//    }
//
//    @Override
//    public void updateDepartment(Department department) {
//        //this.departmentService.update(department);
//    }
//
//    @Override
//    public void deleteDepartment(Integer id) {
//        //this.departmentService.delete(id);
//    }
}
