package by.derevitsky;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class ConfigH2DataBase {
    @Bean
    public DataSource setDataSourceH2inMemory(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .addScript("create_schema.sql")
                .addScript("populate_schema.sql")
                .build();
    }

    @Bean
    @Qualifier("inMemoryH2DataSource")
    public JdbcTemplate jdbcTemplateH2inMemory(){
        JdbcTemplate template = new JdbcTemplate(setDataSourceH2inMemory());
        return template;
    }
}
