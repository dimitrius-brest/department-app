package by.derevitsky;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Runner2 {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("service_context.xml");
        DepartmentService service = (DepartmentService) context.getBean("departmentService");

        // Проверяем метод getAll
        System.out.println("Проверяем слой сервис, метод getAll()");
        List<Department> departments = service.getAll();
        for (Department department : departments) {
            System.out.println("id: " + department.getId() + ", name: " + department.getName());
        }

        // Проверяем метод getById
        System.out.println("\nПроверяем метод getById слоя сервисов");
        Department department = service.getById(3);
        System.out.println("id: " + department.getId() + ", name: " + department.getName());

    }
}
