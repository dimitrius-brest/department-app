package by.derevitsky.soap;

import by.derevitsky.model.Department;

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

        // ---------------
        url = new URL("http://localhost:8080/department-soap/department?wsdl");
        qName = new QName("http://soap.derevitsky.by/", "DepartmentSoapImplService");

        Service service2 = Service.create(url, qName);
        //HelloWorld hello = service.getPort(HelloWorld.class);
        DepartmentSoap depSoap = service2.getPort(DepartmentSoap.class);

        Department department = depSoap.getDepartmentById(33);
        System.out.println("ID = " + department.getId() + "; name = " + department.getName());
    }
}
