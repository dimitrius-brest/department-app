package by.derevitsky.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "by.derevitsky")
public class ConfigDataSource {

    // to get properties of DataSources from Environment, which are set in RestAppInitializer class, in 'rest' module
    private Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    // Getting properties, depending on database type, taken from "profiles.properties"
    // and corresponding .properties file (db-${db.type}.properties).
    // See RestConfig.java in "rest" module, lines @PropertySources
//    private String dbDriver     = env.getProperty("db.driver");
//    private String dbUrl        = env.getProperty("db.url");
//    private String dbUsername   = env.getProperty("db.username");
//    private String dbPassword   = env.getProperty("db.password");

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
//         dataSource.setDriverClassName("org.h2.Driver");
//         dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
//         dataSource.setUsername("sa");
//         dataSource.setPassword("");
//        dataSource.setDriverClassName(dbDriver);
//        dataSource.setUrl(dbUrl);
//        dataSource.setUsername(dbUsername);
//        dataSource.setPassword(dbPassword);
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    // --------------------------- @Profile  mysql --------------------------
    /**
     * Sets DataSource of MySQL server database
     * @return DataSource
     */
    @Profile("mysql")
    @Bean(name="MysqlDataSource")
    public DataSource setDataSourceMysql() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
//        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
//        dataSource.setUsername("root");
//        dataSource.setPassword("1");
//        dataSource.setDriverClassName(dbDriver);
//        dataSource.setUrl(dbUrl);
//        dataSource.setUsername(dbUsername);
//        dataSource.setPassword(dbPassword);
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }
}
