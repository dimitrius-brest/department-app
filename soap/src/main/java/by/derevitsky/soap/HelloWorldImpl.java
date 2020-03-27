package by.derevitsky.soap;

import javax.jws.WebService;

@WebService(endpointInterface = "by.derevitsky.soap.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    @Override
    public String getHelloWorld(String name) {
        return "Hello World with SOAP: " + name;
    }
}
