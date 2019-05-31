package by.derevitsky.web.model;

import by.derevitsky.Department;

/**
 * Additional class to show {@link Department} with Average Salary
 */
public class DepartmentForView extends Department {
    private int averageSalary;
    private boolean hasEmployees;

    // -----------  Constructors  -----------

    //public DepartmentForView() {}

    public DepartmentForView(Department department, int averageSalary, boolean hasEmployees) {
        super(department.getId(), department.getName());
        this.averageSalary = averageSalary;
        this.hasEmployees = hasEmployees;
    }

    // -----------  Getters and setters  -----------

    public int getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(int averageSalary) {
        this.averageSalary = averageSalary;
    }

    public boolean isHasEmployees() {
        return hasEmployees;
    }

    public void setHasEmployees(boolean hasEmployees) {
        this.hasEmployees = hasEmployees;
    }
}
