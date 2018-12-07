package by.derevitsky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@ComponentScan
public class RunnerDao {
    public static void main(String[] args) {
        ApplicationContext contextDao = new AnnotationConfigApplicationContext(RunnerDao.class);
        RunnerDao p = contextDao.getBean(RunnerDao.class);
        p.start();
    }

    @Autowired
    @Qualifier("h2DepDAO")
    private DepartmentDAO departmentDAO;

    @Autowired
    @Qualifier("h2EmpDAO")
    private EmployeeDAO employeeDAO;

    private void start(){
        int id = 2;
        System.out.println("Привет! \nВыберем подразделение с id = " + id);
        Department department = departmentDAO.getById(id);
        System.out.println(department.getId() + " : " + department.getName());

        List<Department> departments = departmentDAO.getAll();
        System.out.println("\nВыберем все подразделения:");
        for (Department dep : departments) {
            System.out.println(dep.getId() + " : " + dep.getName());
        }

        System.out.println("\nВыберем работника с id = " + id);
        Employee employee = employeeDAO.getById(id);
        System.out.println(employee.getId() + " : " + employee.getFirstName() + " "
                + employee.getMiddleName() + " " + employee.getLastName() + " "
                + employee.getBirthDate() + " " + employee.getSalary()
        );

        System.out.println("\nВыберем всех работников:");
        List<Employee> employees = employeeDAO.getAll();
        for (Employee emp : employees){
            System.out.println(emp.getId() + " : " + emp.getFirstName() + " "
                    + emp.getMiddleName() + " " + emp.getLastName() + " "
                    + emp.getBirthDate() + " " + emp.getSalary()
            );
        }
    }
}
