package by.derevitsky.soap;

import by.derevitsky.model.Department;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebService(endpointInterface = "by.derevitsky.soap.DepartmentSoap")
public class DepartmentSoapImpl implements DepartmentSoap {

    // Stub
    private List<Department> departments = new ArrayList<Department>(
            Arrays.asList(
                    new Department(1, "Stub Dep. 1"),
                    new Department(2, "Stub Dep. 2")
            )
    );

    @Override
    public Department[] findAllDepartments() {
        return this.departments.toArray(new Department[this.departments.size()]);
    }

    @Override
    public Department findDepartmentById(Integer id) {
        Department searchedDepartment = new Department(0, "Department not found");
        for (Department department : this.departments){
            if (id == department.getId()) {
                searchedDepartment.setId(id);
                searchedDepartment.setName(department.getName());
                break;
            }
        }
        return searchedDepartment;
    }

    @Override
    public void insertDepartment(Department department) {
        this.departments.add(department);
    }

    @Override
    public void updateDepartment(Department department) {
        for (Department updatedDepartment : this.departments){
            if (updatedDepartment.getId() == department.getId()) {
                updatedDepartment.setName(department.getName());
                break;
            }
        }
    }

    @Override
    public void deleteDepartment(Integer id) {
        for (Department department : this.departments){
            if (id == department.getId()) {
                this.departments.remove(department);
                break;
            }
        }
    }
}
