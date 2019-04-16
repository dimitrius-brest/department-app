package by.derevitsky;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentsWebController {

    /**
     * Gets Departments from REST API,
     * adds "average salary" and "has employees" flag to every Department in list.
     * @return the list of Departments + average salary + has employees flag
     */
    private List<DepartmentForView> getDepartments(){
        List<DepartmentForView> departmentsForView = new ArrayList<DepartmentForView>();

        // Getting array of Departments form REST API
        RestTemplate restTemplate = new RestTemplate();
        String url ="http://localhost:8080/department-rest/departments/all";
        ResponseEntity<Department[]> responseEntity = restTemplate.getForEntity(url, Department[].class);
        Department[] departments = responseEntity.getBody();

        // Counting average salary for each Department in array
        boolean hasEmployees;
        int averageSalary;
        for(Department dep : departments) {
            String url2 = "http://localhost:8080/department-rest/employees/dep/" + dep.getId();
            // Trying to get the list of Employees in the Department
            try {
                ResponseEntity<Employee[]> responseEntity2 = restTemplate.getForEntity(url2, Employee[].class);
                Employee[] employees = responseEntity2.getBody();
                hasEmployees = true;
                int sum = 0;
                // Counting average salary of Employees in the Department
                for (int i = 0; i < employees.length; i++) {
                    sum += employees[i].getSalary();
                }
                averageSalary = sum / employees.length;
            } catch (Exception e) {
                hasEmployees = false;
                averageSalary = 0;
            }
            departmentsForView.add(new DepartmentForView(dep, averageSalary, hasEmployees));
        }
        return departmentsForView;
    }

    /**
     * Show the list of Departments with average salaries
     * @return ModelAndView, showing all Departments
     */
    @GetMapping("/all")
    public ModelAndView showDepartments(){
        List<DepartmentForView> departments = this.getDepartments();

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
