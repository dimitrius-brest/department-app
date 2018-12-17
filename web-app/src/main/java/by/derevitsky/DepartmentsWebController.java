package by.derevitsky;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentsWebController {

    private List<Department> departments = Arrays.asList(
            new Department(1, "Pharaon Administration"),
            new Department(2, "Zhrets Temple"),
            new Department(3, "Irrigation Department")
    );

    @GetMapping("/all")
    public ModelAndView showDepartments(){
        ModelAndView model = new ModelAndView("departments");
        model.addObject("departments");
        return model;
    }
}
