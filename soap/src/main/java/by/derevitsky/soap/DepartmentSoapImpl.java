package by.derevitsky.soap;

//import by.derevitsky.DepartmentService;
import by.derevitsky.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Component
@WebService(endpointInterface = "by.derevitsky.soap.DepartmentSoap")
public class DepartmentSoapImpl /*extends SpringBeanAutowiringSupport*/ implements DepartmentSoap {

    //@Autowired
    //private DepartmentService departmentService;

    //@Autowired
    //private JustTemp justTemp;

    // Stub
    private List<Department> departments = new ArrayList<Department>(
            Arrays.asList(
                    new Department(1, "Stub Dep. 1" /*+ justTemp.getTestMessage()*/),
                    new Department(2, "Stub Dep. 2")
            )
    );

    @Override
    public Department[] getAllDepartments() {
        //return this.departmentService.getAll();

        return this.departments.toArray(new Department[this.departments.size()]);
    }

    @Override
    public Department getDepartmentById(Integer id) {
        Department searchedDepartment = new Department(0, "Department not found");
        for (Department department : this.departments){
            if (id == department.getId()) {
                searchedDepartment.setId(id);
                searchedDepartment.setName(department.getName());
                break;
            }
        }
        return searchedDepartment;
        //return this.departmentService.getById(id);
    }

    @Override
    public void insertDepartment(Department department) {
        //this.departmentService.insert(department);
        this.departments.add(department);
    }

    @Override
    public void updateDepartment(Department department) {
        //this.departmentService.update(department);
        for (Department updatedDepartment : this.departments){
            if (updatedDepartment.getId() == department.getId()) {
                updatedDepartment.setName(department.getName());
                break;
            }
        }
    }

    @Override
    public void deleteDepartment(Integer id) {
        //this.departmentService.delete(id);
        for (Department department : this.departments){
            if (id == department.getId()) {
                this.departments.remove(department);
                break;
            }
        }
    }
}
