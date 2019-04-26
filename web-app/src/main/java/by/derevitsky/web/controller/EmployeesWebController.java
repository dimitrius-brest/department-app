package by.derevitsky.web.controller;

import by.derevitsky.Department;
import by.derevitsky.Employee;
import by.derevitsky.web.service.DepartmentsWebService;
import by.derevitsky.web.service.EmployeesWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeesWebController {

    @Autowired
    private EmployeesWebService employeesWebService;
    @Autowired
    private DepartmentsWebService departmentsWebService;

    /**
     * Show the whole list of Employees
     * @return
     */
    @GetMapping("/all")
    public ModelAndView showEmployees(){
        List<Employee> employees = employeesWebService.getAllEmployees();
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
        List<Employee> employees = employeesWebService.getEmployeesByDepartmentId(id);
        Department department = departmentsWebService.getDepartmentById(id);
        model.addAttribute("employees", employees);
        model.addAttribute("department", department);
        return "employees";
    }

    /**
     * Show the Form to add an Employee to the Department with "id"
     * @param model
     * @param idDepartment The "id" of the Department
     * @return
     */
    @GetMapping("/add/{idDepartment}")
    public String showAddEmployeeForm(Model model, @PathVariable("idDepartment") Integer idDepartment){
        Employee employee = new Employee();
        employee.setIdDepartment(idDepartment);
        employee.setId(0);
        //employee.setBirthDate(LocalDate.parse("1997-05-05"));
        Department department = departmentsWebService.getDepartmentById(idDepartment);
        model.addAttribute("employee", employee);
        model.addAttribute("department", department);
        return "employee_add";
    }

    @PostMapping("/add/{idDepartment}")
    public String addEmployee(@ModelAttribute("employee") Employee employee, BindingResult result, ModelMap model,
                              @PathVariable("idDepartment") Integer idDepartment) {
        if(result.hasErrors()){
            return "error";
        }
        Integer iidd = idDepartment;
        //employeesWebService.addEmployee(employee);
        return "redirect:/employees/" + idDepartment;
    }

    /**
     * Update the Employee
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/update/{id}")
    public String showUpdateEmployeeForm(Model model, @PathVariable("id") Integer id){
        //Employee employee = employeesWebService.getEmployeeById(id);
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
        Integer departmentId = employeesWebService.getEmployeeById(id).getIdDepartment();
        employeesWebService.deleteEmployee(id);
        return "redirect:/employees/" + departmentId;
    }
}
