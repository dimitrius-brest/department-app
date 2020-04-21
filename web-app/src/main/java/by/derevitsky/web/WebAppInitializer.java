package by.derevitsky.web;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Nullable
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Nullable
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
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
        String viewType;
        String myProfiles = "";

        try (InputStream input = WebAppInitializer.class.getClassLoader().getResourceAsStream("profiles.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            viewType = prop.getProperty("view.type");           // the type of view: JSP or Thymeleaf
            myProfiles = viewType;                              //
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        servletContext.setInitParameter("spring.profiles.active", myProfiles);   // <--- profile is selected from file

    }
}
