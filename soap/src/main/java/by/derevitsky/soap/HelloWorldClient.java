package by.derevitsky.soap;

import by.derevitsky.model.Department;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

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

        System.out.println("------ Get by id: -------");
        Department department = depSoap.getDepartmentById(2);
        System.out.println("ID = " + department.getId() + "; name = " + department.getName());

        System.out.println("------ Get all: -------");
        List<Department> departments = Arrays.asList(depSoap.getAllDepartments());
        for (Department dep: departments) {
            System.out.println("ID: " + dep.getId() + "; name: " + dep.getName());
        }

        System.out.println("----- Inserting new department -----");
        depSoap.insertDepartment(new Department(3, "Stub Dep. 3"));
        departments = Arrays.asList(depSoap.getAllDepartments());
        for (Department dep: departments) {
            System.out.println("ID: " + dep.getId() + "; name: " + dep.getName());
        }

        System.out.println("----- Updating a department -----");
        depSoap.updateDepartment(new Department(2, "Updated Stub Dep. 222"));
        departments = Arrays.asList(depSoap.getAllDepartments());
        for (Department dep: departments) {
            System.out.println("ID: " + dep.getId() + "; name: " + dep.getName());
        }

        System.out.println("----- Deleting a department -----");
        depSoap.deleteDepartment(2);
        departments = Arrays.asList(depSoap.getAllDepartments());
        for (Department dep: departments) {
            System.out.println("ID: " + dep.getId() + "; name: " + dep.getName());
        }
    }
}
