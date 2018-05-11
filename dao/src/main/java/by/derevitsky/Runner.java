package by.derevitsky;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws SQLException {

        // Контекст Spring
        ApplicationContext context = new ClassPathXmlApplicationContext("springcontext.xml");
        // Получаем из контекста departmentDAO
        DepartmentDAO departmentDAO = (DepartmentDAO) context.getBean("departmentDAO");


        // Проверяем метод getAll
        System.out.println("Таблица DEPARTMENTS:");
        List<Department> departments = departmentDAO.getAll();
        for (Department department : departments) {
            System.out.println("id: " + department.getId() + ", name: " + department.getName());
        }
        System.out.println();

        // Получаем из контекста employeeDAO
        EmployeeDAO employeeDAO = (EmployeeDAO) context.getBean("employeeDAO");

        // Проверяем метод getAll
        System.out.println("Таблица EMPLOYEES:");
        List<Employee> employees = employeeDAO.getAll();
        for (Employee employee : employees) {
            System.out.println(
                employee.getId() + " " + employee.getId_department() + " " +
                employee.getFirst_name() + " " + employee.getMiddle_name() + " " + employee.getLast_name() + " " +
                employee.getBirth_date() + " " + employee.getSalary());
        }

    }
}
