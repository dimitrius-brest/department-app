package by.derevitsky.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.derevitsky.web")
//@PropertySource("classpath:profiles.properties")
public class WebConfig {

    @Bean
    ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    // The bean of RestTemplate for services to consume data from REST API
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    // To get parameters from properties file.
    // See: https://stackoverflow.com/questions/33714491/whats-the-best-way-to-add-a-new-property-source-in-spring
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
//        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
//        pspc.setIgnoreUnresolvablePlaceholders(Boolean.TRUE);
//        pspc.setIgnoreResourceNotFound(Boolean.TRUE);
//        return pspc;
//    }
}
