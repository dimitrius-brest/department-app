package by.derevitsky.soap;

import by.derevitsky.DepartmentService;
import by.derevitsky.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "by.derevitsky.soap.DepartmentSoap")
@Service
public class DepartmentSoapImpl implements DepartmentSoap {

    @Autowired
    private DepartmentService departmentService;

    @Override
    public Department[] findAllDepartments() {
        List<Department> foundDepartments = this.departmentService.getAll();
        return foundDepartments.toArray(new Department[foundDepartments.size()]);
    }

    @Override
    public Department findDepartmentById(Integer id) {
        Department foundDepartment = this.departmentService.getById(id);
        return foundDepartment;
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
