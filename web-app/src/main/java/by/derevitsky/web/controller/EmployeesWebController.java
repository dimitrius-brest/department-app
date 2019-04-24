package by.derevitsky.web.controller;

import by.derevitsky.Employee;
import by.derevitsky.web.service.EmployeesWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeesWebController {

    @Autowired
    private EmployeesWebService webService;

    /**
     * Show the whole list of Employees
     * @return
     */
    @GetMapping("/all")
    public ModelAndView showEmployees(){
//        RestTemplate restTemplate = new RestTemplate();
//        String url ="http://localhost:8080/department-rest/employees/all";
//        ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity(url, Employee[].class);
//        Employee[] employees = responseEntity.getBody();

        List<Employee> employees = webService.getAllEmployees();
        ModelAndView model = new ModelAndView("employees");
        model.addObject("employees", employees);
        return model;
    }

    /**
     * Show the list of Employees of certain Department by "Department ID"
     * @param model
     * @param id
     * @return View "employees.jsp"
     */
    @GetMapping("/{id}")
    public String showEmployeesByDepartmentId(Model model, @PathVariable("id") Integer id){
        List<Employee> employees = webService.getEmployeesByDepartmentId(id);
        model.addAttribute("employees", employees);
        return "employees";
    }

    /**
     * Form to add an Employee
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String showAddEmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee_add";
    }

    /**
     * Update the Employee
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/update/{id}")
    public String showUpdateEmployeeForm(Model model, @PathVariable("id") Integer id){
        //Employee employee = webService.getEmployeeById(id);
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee_update";
    }

    /**
     * Delete the Employee with "id"
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        webService.deleteEmployee(id);
        return "redirect:/departments/all";
    }
}
