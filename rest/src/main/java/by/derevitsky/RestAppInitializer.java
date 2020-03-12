package by.derevitsky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RestAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Nullable
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RestConfig.class};
    }

    @Nullable
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{RestConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //  To select a Profile.
    //  See: https://stackoverflow.com/questions/38321419/how-to-set-active-profiles-in-spring-annotation-based-java-config
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        // Getting profiles from properties file "profiles.properties"
        // See: https://mkyong.com/java/java-properties-file-examples/
        //     "3. Load a properties file from classpath"
        String dbType;
        String accessType;
        String myProfiles = "";

        try (InputStream input = RestAppInitializer.class.getClassLoader().getResourceAsStream("profiles.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            dbType = prop.getProperty("db.type");               // the type of database: h2mem, h2server, mysql
            accessType = prop.getProperty("access.type");       // the access type to database: jdbc, jpa
            //myProfiles = prop.getProperty("my.profiles");
            myProfiles = accessType + "," + dbType;             // combination of access type and db type: "jdbc,h2mem" and so on
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //servletContext.setInitParameter("spring.profiles.active", "jdbc");   // <--- Change active profile here
        //servletContext.setInitParameter("spring.profiles.active", "jpa");   // <--- Change active profile here
        //servletContext.setInitParameter("spring.profiles.active", "jdbc,h2mem");
        servletContext.setInitParameter("spring.profiles.active", myProfiles);   // <--- profile is selected from file

    }
}
