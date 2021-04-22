package by.derevitsky.soap;

// See:  https://www.baeldung.com/apache-cxf-with-spring
// and:  https://github.com/eugenp/tutorials/tree/master/apache-cxf/cxf-spring

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SoapAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SoapConfiguration.class);
        container.addListener(new ContextLoaderListener(context));

        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new CXFServlet());
        dispatcher.addMapping("/services/*");


        // Getting profiles from properties file "profiles.properties"
        // See: https://mkyong.com/java/java-properties-file-examples/
        //     "3. Load a properties file from classpath"
        String dbType;
        String accessType;
        String myProfiles = "";

        try (InputStream input = SoapAppInitializer.class.getClassLoader().getResourceAsStream("profiles.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            dbType = prop.getProperty("db.type");               // the type of database: h2mem, h2server, mysql
            accessType = prop.getProperty("access.type");       // the access type to database: jdbc, jpa
            myProfiles = accessType + "," + dbType;             // combination of access type and db type: "jdbc,h2mem" and so on
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        container.setInitParameter("spring.profiles.active", myProfiles);   // <--- profile is selected from file
    }
}
