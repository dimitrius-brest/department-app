package by.derevitsky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentsWebController {

    @Autowired
    private DepartmentsWebService webService;

    /**
     * Show the list of Departments with average salaries
     * @return ModelAndView, showing all Departments
     */
    @GetMapping("/all")
    public ModelAndView showDepartments(){
        List<DepartmentForView> departments = webService.getDepartments(); //this.getDepartments();

        ModelAndView model = new ModelAndView("departments");
        model.addObject("departments", departments);
        return model;
    }


    @GetMapping("/add")
    public String showAddDepartmentForm(Model model){
        Department department = new Department();
        model.addAttribute("department", department);
        return "add_department";
    }

    /**
     * Handling the form that adds a new Department
     * @param department
     * @param result
     * @param model
     * @return
     */
    // See:  https://www.baeldung.com/spring-mvc-form-tutorial
    //       https://www.mkyong.com/spring-mvc/spring-mvc-form-handling-example/
    //       http://streletzcoder.ru/rabota-s-formami-v-spring-mvc/
    @PostMapping("/add")
    public String addDepartment(@ModelAttribute("department")Department department,
                                      BindingResult result, ModelMap model){
        if(result.hasErrors()){
            return "error";
        }
        //model.addAttribute(department.getName());

        return "departments";
    }
}
