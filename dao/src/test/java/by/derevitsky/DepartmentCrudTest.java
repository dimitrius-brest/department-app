package by.derevitsky;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class DepartmentCrudTest {
    @Test
    public void testGetAll() throws Exception {
        // Контекст Spring
        ApplicationContext context = new ClassPathXmlApplicationContext("dao_context.xml");
        // Получаем из контекста departmentDAO
        DepartmentDAO departmentDAO = (DepartmentDAO) context.getBean("departmentDAO");

        List<Department> departments = departmentDAO.getAll();
        Assert.assertNotNull(departments);                   //  База не пустая
        Assert.assertTrue(departments.size() == 3);  //  В базе 3 тестовые записи
    }
}
