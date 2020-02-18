package by.derevitsky.dao;

import by.derevitsky.model.Department;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository("JpaDepartmentDAO")
@Profile("jpa")
public class DepartmentDAOImplJpa implements DepartmentDAO {
    // Stub
    List<Department> departments = new ArrayList<Department>(
        Arrays.asList(
            new Department(1, "Stub Department 1"),
            new Department(2, "Stub Department 2")
        )
    );

    @Override
    public List<Department> getAll() {
        return departments;
    }

    @Override
    public Department getById(int id) {
        Department searchedDepartment = new Department(0, "Department not found");
        for (Department department : departments){
            if (id == department.getId()) {
                searchedDepartment.setId(id);
                searchedDepartment.setName(department.getName());
                break;
            }
        }
        return searchedDepartment;
    }

    @Override
    public void insert(Department department) {
        departments.add(department);
    }

    @Override
    public void update(Department department) {
        for (Department updatedDepartment : departments){
            if (updatedDepartment.getId() == department.getId()) {
                updatedDepartment.setName(department.getName());
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        for (Department department : departments){
            if (id == department.getId()) {
                departments.remove(department);
                break;
            }
        }
    }
}
