package by.derevitsky;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentsWebController {

    private List<DepartmentForView> getDepartments(){
        RestTemplate restTemplate = new RestTemplate();
        String url ="http://localhost:8080/department-rest/departments/all";
        ResponseEntity<Department[]> responseEntity = restTemplate.getForEntity(url, Department[].class);
        Department[] departments = responseEntity.getBody();

        //TODO доделать
        //DepartmentForView[] departmentsForView = new DepartmentForView[departments.length];
        List<DepartmentForView> departmentsForView = new ArrayList<DepartmentForView>();
        for(Department dep : departments) {
            departmentsForView.add(new DepartmentForView(dep, 555, true));
        }
        return departmentsForView;
    }

    private List<Department> departmentsStub = Arrays.asList(
            new Department(1, "Pharaon Administration"),
            new Department(2, "Zhrets Temple"),
            new Department(3, "Irrigation Department")
    );

    @GetMapping("/all")
    public ModelAndView showDepartments(){
//        RestTemplate restTemplate = new RestTemplate();
//        String url ="http://localhost:8080/department-rest/departments/all";
//        ResponseEntity<Department[]> responseEntity = restTemplate.getForEntity(url, Department[].class);
//        Department[] departments = responseEntity.getBody();

        List<DepartmentForView> departments = this.getDepartments();

        ModelAndView model = new ModelAndView("departments");
        //TODO доделать
        //model.addObject("departments", departments);
        model.addObject("departments", departments);
        return model;
    }
}
