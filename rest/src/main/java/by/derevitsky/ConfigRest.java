package by.derevitsky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.derevitsky")
@PropertySources({
        @PropertySource("classpath:profiles.properties"),
        @PropertySource("classpath:db-${db.type}.properties")
})
public class ConfigRest implements WebMvcConfigurer {

    @Bean
    ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    // To get parameters from properties file.
    // See: https://stackoverflow.com/questions/33714491/whats-the-best-way-to-add-a-new-property-source-in-spring
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        pspc.setIgnoreUnresolvablePlaceholders(Boolean.TRUE);
        pspc.setIgnoreResourceNotFound(Boolean.TRUE);
        return pspc;
    }

//    @Value("${db.driver}")
//    private String dbDriver;
//
//    @Value("${db.url}")
//    private String dbUrl;
//
//    @Value("${db.username}")
//    private String dbUsername;
//
//    @Value("${db.password}")
//    private String dbPassword;
//
//    @Bean(name = "dbProperties")
//    public Properties dpProperties() {
//        Properties props = new Properties();
//        props.setProperty("dbDriver", dbDriver);
//        props.setProperty("dbUrl", dbUrl);
//        props.setProperty("dbUsername", dbUsername);
//        props.setProperty("dbPassword", dbPassword);
//        return props;
//    }

}
