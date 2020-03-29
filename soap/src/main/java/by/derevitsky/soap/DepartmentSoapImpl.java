package by.derevitsky.soap;

import by.derevitsky.DepartmentService;
import by.derevitsky.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.List;

//@Component
@WebService(endpointInterface = "by.derevitsky.soap.DepartmentSoap")
public class DepartmentSoapImpl implements DepartmentSoap {

    @Autowired
    private DepartmentService departmentService;

    @Override
    public List<Department> getAllDepartments() {
        return this.departmentService.getAll();
    }

    @Override
    public Department getDepartmentById(Integer id) {
        return this.departmentService.getById(id);
    }

    @Override
    public void insertDepartment(Department department) {
        this.departmentService.insert(department);
    }

    @Override
    public void updateDepartment(Department department) {
        this.departmentService.update(department);
    }

    @Override
    public void deleteDepartment(Integer id) {
        this.departmentService.delete(id);
    }
}
