package by.derevitsky.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "by.derevitsky.database")
public class ConfigH2tempDataSource {

    /**
     * Sets DataSource of H2 in-memory database
     * @return new in-memory database instance, populated with test data from *.sql files
     */
    @Profile("h2temp")
    @Bean(name="H2tempDataSource")
    public DataSource setDataSourceH2inMemory(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("h2temp;DATABASE_TO_UPPER=false")
                .setScriptEncoding("UTF-8")
                .addScript("h2temp_create_schema.sql")
                .addScript("h2temp_populate_schema.sql")
                .build();
    }

    // ----- For JPA persistence unit -----
    @Profile("h2temp")
    @Bean(name="H2tempProperties")
    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return properties;
    }
}
