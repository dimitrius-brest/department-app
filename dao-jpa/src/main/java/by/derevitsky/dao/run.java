package by.derevitsky.dao;

import by.derevitsky.model.Department;
import by.derevitsky.model.Employee;

import java.time.LocalDate;
import java.util.List;

public class run {
    public static void main(String[] args) {
        DepartmentDAO departmentDAO = new DepartmentDAOJpa();
        EmployeeDAO employeeDAO = new EmployeeDAOJpa();

        List<Department> departments = departmentDAO.getAll();
        System.out.println(departments.toString());

        Department departmentById = departmentDAO.getById(3);
        System.out.println(departmentById.toString());

        departmentDAO.insert(new Department(3, "New Department"));
        System.out.println(departmentDAO.getAll().toString());

        System.out.println("Updating a department...");
        departmentDAO.update(new Department(2, "Updated Department"));
        System.out.println(departmentDAO.getAll().toString());

        System.out.println("Removing a deparment...");
        departmentDAO.delete(2);
        System.out.println(departmentDAO.getAll().toString());

        System.out.println("-----  Employees -----");
        System.out.println(employeeDAO.getAll().toString());

        System.out.println(employeeDAO.getById(4).toString()); // Get By ID

        System.out.println(employeeDAO.getByDepartmentId(2).toString()); // Get By Department_ID

        employeeDAO.insert(
                new Employee(4, 2, "Sergey", "Alexandrovich", "Esenin",
                LocalDate.parse("2020-02-02"), 2000)
        );
        System.out.println(employeeDAO.getAll().toString());   //  Insert

        System.out.println("Updating employe...");
        employeeDAO.update(
                new Employee(2, 1, "Johh", "Johnovich", "Johnson",
                        LocalDate.parse("2019-09-09"), 999)
        );
        System.out.println(employeeDAO.getAll().toString());

        System.out.println("Removing an employee");
        employeeDAO.delete(2);
        System.out.println(employeeDAO.getAll().toString());
    }
}
