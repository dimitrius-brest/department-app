package by.derevitsky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@ComponentScan
public class RunnerService {
    public static void main(String[] args) {
        ApplicationContext contextService = new AnnotationConfigApplicationContext(RunnerService.class);
        RunnerService p = contextService.getBean(RunnerService.class);
        p.start();
    }

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    private void start(){

        // Проверяем метод getAll
        System.out.println("Проверяем слой Сервис, метод getAll() для Department");
        List<Department> departments = departmentService.getAll();
        for (Department department : departments) {
            System.out.println("id: " + department.getId() + ", name: " + department.getName());
        }

        // Проверяем метод getById
        System.out.println("\nПроверяем метод getById слоя Cервисов для Department");
        Department department = departmentService.getById(2);
        System.out.println("id: " + department.getId() + ", name: " + department.getName());

        //
        System.out.println("\nВыведем всех работников:");
        List<Employee> employees = employeeService.getAll();
        for (Employee employee : employees) {
            System.out.println(employee.getId() + " " + employee.getFirstName() + " " +
                        employee.getMiddleName() + " " + employee.getLastName() + " " +
                        employee.getBirthDate() + " " + employee.getSalary());
        }

    }
}
