package by.derevitsky.soap;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class HelloWorldClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/department-soap/hello?wsdl");
        QName qName = new QName("http://soap.derevitsky.by/", "HelloWorldImplService");

        Service service = Service.create(url, qName);
        HelloWorld hello = service.getPort(HelloWorld.class);

        System.out.println(hello.getHelloWorld("Dima Ddd"));
    }
}
