package by.derevitsky;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/departments")
public class DepartmentsWebController {

    /*private List<Department> departmentsStub = Arrays.asList(
            new Department(1, "Pharaon Administration"),
            new Department(2, "Zhrets Temple"),
            new Department(3, "Irrigation Department")
    );*/

    @GetMapping("/all")
    public ModelAndView showDepartments(){
        RestTemplate restTemplate = new RestTemplate();
        String url ="http://localhost:8080/department-rest/departments/all";
        ResponseEntity<Department[]> responseEntity = restTemplate.getForEntity(url, Department[].class);
        Department[] departments = responseEntity.getBody();

        ModelAndView model = new ModelAndView("departments");
        model.addObject("departments", departments);
        return model;
    }
}
