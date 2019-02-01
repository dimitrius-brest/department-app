package by.derevitsky;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Spring configuration file
 * @author Dmitry Derevitsky
 */
@Configuration
public class ConfigH2DataBase {
    /**
     * Sets DataSource of H2 in-memory database
     * @return new in-memory database instance, populated with test data from *.sql files
     */
    @Bean
    public DataSource setDataSourceH2inMemory(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .addScript("create_schema.sql")
                .addScript("populate_schema.sql")
                .build();
    }
    /**
     * Creates JdbcTemplate bean.
     * @return new JdbcTemplate instance
     */
    @Bean
    @Qualifier("inMemoryH2DataSource")
    public JdbcTemplate jdbcTemplateH2inMemory(){
        JdbcTemplate template = new JdbcTemplate(setDataSourceH2inMemory());
        return template;
    }
}
