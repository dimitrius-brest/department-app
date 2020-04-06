package by.derevitsky.soap;

import com.sun.xml.ws.transport.http.servlet.WSServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "department", urlPatterns = "/department", loadOnStartup = 1)
public class ServletDepartment extends WSServlet {
}
