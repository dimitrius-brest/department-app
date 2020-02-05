package by.derevitsky;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

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
        //servletContext.setInitParameter("spring.profiles.active", "jdbc-h2");   // <--- Change active profile here
        servletContext.setInitParameter("spring.profiles.active", "jpa-mysql");   // <--- Change active profile here
    }
}
