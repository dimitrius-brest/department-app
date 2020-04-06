package by.derevitsky.soap;

import com.sun.xml.ws.transport.http.servlet.WSServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "employee", urlPatterns = "/employee", loadOnStartup = 1)
public class ServletEmployee extends WSServlet {
}
