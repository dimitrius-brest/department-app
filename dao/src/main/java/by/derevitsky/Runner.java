package by.derevitsky;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        /* обычный JDBC
        try {
            // Создаём соединение
            Connection connection = dataSource.getConnection();
            System.out.println("Connection established...");
            // Приготавливаем и выполняем запрос
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Departments");
            ResultSet resultSet = statement.executeQuery();
            // Выводим результаты запроса в консоль
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println(id + ": " + name);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
    }
}
