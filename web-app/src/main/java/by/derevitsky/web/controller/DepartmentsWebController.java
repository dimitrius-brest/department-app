package by.derevitsky.web.controller;

import by.derevitsky.Department;
import by.derevitsky.web.model.DepartmentForView;
import by.derevitsky.web.service.DepartmentsWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentsWebController {

    @Autowired
    private DepartmentsWebService webService;

    /**
     * Redirection from root directory to "/departments/all"
     * @return
     */
    @GetMapping("/")
    public String redirectToShowDepartments() {
        return "redirect:/departments/all";
    }

    /**
     * Show the list of Departments with average salaries
     *
     * @return ModelAndView, showing all Departments
     */
    @GetMapping("/all")
    public ModelAndView showDepartments() {
        List<DepartmentForView> departments = webService.getDepartments();
        ModelAndView model = new ModelAndView("departments");
        model.addObject("departments", departments);
        return model;
    }


    /**
     * Show the Form to add a Department
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String showAddDepartmentForm(Model model) {
        Department department = new Department();
        model.addAttribute("department", department);
        return "department_add";
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
    public String addDepartment(@ModelAttribute("department") @Valid Department department,
                                BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "department_add";
        }
        //model.addAttribute(department.getName());
        webService.addDepartment(department);
        return "redirect:/departments/all";
    }

    /**
     * Show the Form to update the Department with "id"
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/update/{id}")
    public String showUpdateDepartmentForm(Model model, @PathVariable("id") Integer id){
        Department department = webService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "department_update";
    }

    /**
     * Update the Department
     * @param department
     * @return
     */
    @PostMapping("/update")
    public String updateDepartment(@ModelAttribute("department") @Valid Department department,
                                   BindingResult result, Model model){
        if(result.hasErrors()){
            return "department_update";
        }
        webService.updateDepartment(department);
        return "redirect:/departments/all";
    }

    /**
     * Delete the Department with "id"
     * @param id
     */
    @PostMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable("id") Integer id){
        webService.deleteDepartment(id);
        return "redirect:/departments/all";
    }

}



