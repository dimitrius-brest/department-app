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
        departmentDAO.testMethod("тадам....");

        // Проверяем метод getAll
        List<Department> departments = departmentDAO.getAll();
        for (Department department : departments) {
            System.out.println("id: " + department.getId() + ", name: " + department.getName());
        }

    }
}