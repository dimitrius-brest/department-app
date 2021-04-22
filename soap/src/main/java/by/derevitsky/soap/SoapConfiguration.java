package by.derevitsky.soap;

// See:  https://www.baeldung.com/apache-cxf-with-spring
// and:  https://github.com/eugenp/tutorials/tree/master/apache-cxf/cxf-spring

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.xml.ws.Endpoint;

@Configuration
@ComponentScan(basePackages = "by.derevitsky")
@PropertySources({
        @PropertySource("classpath:profiles.properties"),
        @PropertySource("classpath:db-${db.type}.properties")
})
public class SoapConfiguration {

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), new DepartmentSoapImpl());
        endpoint.publish("/dep");
        return endpoint;
    }

    @Bean
    public Endpoint endpointOp() {
        EndpointImpl endpointOp = new EndpointImpl(springBus(), new OperationsImpl());
        endpointOp.publish("/op");
        return endpointOp;
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
}
