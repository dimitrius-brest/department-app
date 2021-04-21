package by.derevitsky.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface Operations {

    @WebMethod
    public int add(int a, int b);
}
