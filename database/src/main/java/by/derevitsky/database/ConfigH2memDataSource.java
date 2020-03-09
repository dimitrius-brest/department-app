package by.derevitsky.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "by.derevitsky")
public class ConfigH2memDataSource {

    // --------------------------- @Profile  h2mem --------------------------
    /**
     * Sets DataSource of H2 in-memory database
     * @return new in-memory database instance, populated with test data from *.sql files
     */
    @Profile("h2mem")
    @Bean(name="H2memDataSource")
    public DataSource setDataSourceH2inMemory(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("h2mem;DATABASE_TO_UPPER=false")
                .setScriptEncoding("UTF-8")
                .addScript("h2mem_create_schema.sql")
                .addScript("h2mem_populate_schema.sql")
                .build();
    }

    // ----- For JPA persistence unit -----
    @Profile("h2mem")
    @Bean(name="H2memProperties")
    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return properties;
    }

    // --------------------------- @Profile  h2server --------------------------
    /**
     * Sets DataSource of H2 server database
     * @return DataSource
     */
    @Profile("h2server")
    @Bean(name="H2serverDataSource")
    public DataSource setDataSourceH2server(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
}
