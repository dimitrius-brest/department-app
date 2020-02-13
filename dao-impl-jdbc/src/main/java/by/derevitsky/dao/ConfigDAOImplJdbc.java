package by.derevitsky.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Spring configuration file
 * @author Dmitry Derevitsky
 */
@Configuration
@EnableTransactionManagement
public class ConfigDAOImplJdbc {

    @Autowired
    //@Qualifier("NewDataSource")
    private DataSource dataSource;

    /**
     * Creates JdbcTemplate bean.
     * @return new JdbcTemplate instance
     */
    @Bean
    @Qualifier("inMemoryH2DataSource")
    public JdbcTemplate jdbcTemplateH2inMemory(){
        //JdbcTemplate template = new JdbcTemplate(setDataSourceH2inMemory());
        JdbcTemplate template = new JdbcTemplate(dataSource);
        return template;
    }

//    /**
//     * Sets Transaction manager
//     * @return
//     */
//    @Bean
//    public PlatformTransactionManager transactionManager(){
//        return new DataSourceTransactionManager(jdbcTemplateH2inMemory().getDataSource());
//    }

}
