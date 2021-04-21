package by.derevitsky.soap;

import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;

@WebService(endpointInterface = "by.derevitsky.soap.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    @Autowired
    private JustTemp justTemp;

    @Override
    public String getHelloWorld(String name) {
        return "Hello World with SOAP: " + name + "; " + justTemp.getTestMessage();
    }
}
