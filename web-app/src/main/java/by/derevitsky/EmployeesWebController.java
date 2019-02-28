package by.derevitsky;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/employees")
public class EmployeesWebController {

    @GetMapping("/all")
    public ModelAndView showEmployees(){
        RestTemplate restTemplate = new RestTemplate();
        String url ="http://localhost:8080/department-rest/employees/all";
        ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity(url, Employee[].class);
        Employee[] employees = responseEntity.getBody();

        ModelAndView model = new ModelAndView("employees");
        model.addObject("employees", employees);
        return model;
    }
}
