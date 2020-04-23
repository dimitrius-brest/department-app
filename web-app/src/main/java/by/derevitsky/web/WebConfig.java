package by.derevitsky.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.derevitsky.web")
//@PropertySource("classpath:profiles.properties")
public class WebConfig implements WebMvcConfigurer {

/**
 * You can switch between two view resolvers - JSP or Thymeleaf -
 * by changing "view.type" parameter in "profiles.properties" file
 * either to "jsp" or to "thymeleaf"
 */

// ------------------------- JSP -------------------------

    /**
     * To switch to JSP view resolver - set "view.type" in "profiles.properties" file to "jsp"
     */

    @Bean
    @Profile("jsp")
    ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setOrder(1);
        return resolver;
    }

// ------------------------- Thymeleaf -------------------------

    /**
     * To switch to Thymeleaf view resolver - set "view.type" in "profiles.properties" file to "thymeleaf"
     */

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    @Profile("thymeleaf")
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/thymeleaf/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    @Profile("thymeleaf")
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

//    @Override
//    @Profile("thymeleaf")
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setTemplateEngine(templateEngine());
//        registry.viewResolver(resolver);
//    }

    @Bean
    @Profile("thymeleaf")
    ThymeleafViewResolver configureViewResolvers() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setOrder(2);
        return resolver;
    }

// ------------------------- ... -------------------------

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
