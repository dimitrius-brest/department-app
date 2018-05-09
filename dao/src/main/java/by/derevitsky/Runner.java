package by.derevitsky;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Runner {
    public static void main(String[] args) throws SQLException {
        //Department dep = new Department();
        //dep.setName("Ministry");

        // Получает dataSource из контекста Spring
        ApplicationContext context = new ClassPathXmlApplicationContext("springcontext.xml");
        DriverManagerDataSource dataSource = (DriverManagerDataSource) context.getBean("dataSource");

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
