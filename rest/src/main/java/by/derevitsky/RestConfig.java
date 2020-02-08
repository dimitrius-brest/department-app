package by.derevitsky;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.derevitsky")
public class RestConfig implements WebMvcConfigurer {

    @Bean
    ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    // To get parameters from propertie file.
    // See: http://littlebigextra.com/how-to-read-different-properties-file-based-on-spring-profile-in-a-spring-mvc-project/

    // Note: PropertyPlaceholderConfigurer is deprecated.
    // See instead: PropertySourcesPlaceholderConfigurer
    // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/support/PropertySourcesPlaceholderConfigurer.html

    /*@Bean
    public static PropertyPlaceholderConfigurer properties(){
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        Resource resource = new ClassPathResource("profiles.properties");
        ppc.setLocation(resource);
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }*/

}
