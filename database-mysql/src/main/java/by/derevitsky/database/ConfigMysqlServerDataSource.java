package by.derevitsky.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "by.derevitsky.database")
public class ConfigMysqlServerDataSource {

    @Profile("mysql")
    @Bean(name="MysqlDataSource")
    public DataSource setDataSourceMysql() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("1");
        return dataSource;
    }
}
