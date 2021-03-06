package by.derevitsky.web.controller;

import by.derevitsky.model.Department;
import by.derevitsky.model.Employee;
import by.derevitsky.web.model.DateRangeForSearch;
import by.derevitsky.web.model.DepartmentForView;
import by.derevitsky.web.service.DepartmentsWebService;
import by.derevitsky.web.service.EmployeesWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
        DateRangeForSearch dateRange =
                new DateRangeForSearch(/*LocalDate.parse("2000-01-01"), LocalDate.parse("2000-01-01")*/);
        model.addAttribute("employees", employees);
        model.addAttribute("department", department);
        model.addAttribute("date_range", dateRange);
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
        Department department = departmentsWebService.getDepartmentById(idDepartment);
        model.addAttribute("employee", employee);
        model.addAttribute("department", department);
        return "employee_add";
    }

    /**
     * Handling the form that adds a new Employee
     * @param employee
     * @param result
     * @param model
     * @param idDepartment the Id of the Department
     * @return view that shows updated Employees list of the Department
     */
    @PostMapping("/add/{idDepartment}")
    public String addEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult result, ModelMap model,
                              @PathVariable("idDepartment") Integer idDepartment) {
        if(result.hasErrors()){
            Department department = departmentsWebService.getDepartmentById(idDepartment);
            model.addAttribute("department", department);
            return "employee_add";
        }
        employeesWebService.addEmployee(employee);
        return "redirect:/employees/" + idDepartment;
    }

    /**
     * Show the Form to update the Employee with "id"
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/update/{id}")
    public String showUpdateEmployeeForm(Model model, @PathVariable("id") Integer id){
        Employee employee = employeesWebService.getEmployeeById(id);
        Department department = departmentsWebService.getDepartmentById(employee.getIdDepartment());
        List<DepartmentForView> departments = departmentsWebService.getDepartments();
        model.addAttribute("employee", employee);
        model.addAttribute("department", department);
        model.addAttribute("departments", departments);
        return "employee_update";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute("employee") @Valid Employee employee,
                                 BindingResult result, Model model){
        if(result.hasErrors()){
            Department department = departmentsWebService.getDepartmentById(employee.getIdDepartment());
            List<DepartmentForView> departments = departmentsWebService.getDepartments();
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            return "employee_update";
        }
        employeesWebService.updateEmployee(employee);
        return "redirect:/employees/" + employee.getIdDepartment();
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

    /**
     * Search Employees by Birth Date
     * @param idDepartment the ID of the Department where to search
     * @param dateRange the Range of Birth Dates to search
     * @return
     */
    @PostMapping("/search/{idDepartment}")
    public String searchEmployees(Model model, @PathVariable("idDepartment") Integer idDepartment,
                                  @ModelAttribute("date_range") DateRangeForSearch dateRange){
        Department department = departmentsWebService.getDepartmentById(idDepartment);
        List<Employee> employees = employeesWebService.getEmployeesByDepartmentId(idDepartment);
        List<Employee> foundEmployees = new ArrayList<>();

        if(!dateRange.isValid()){    //  Start date is later than End date
            model.addAttribute("department", department);
            model.addAttribute("employees", employees);
            model.addAttribute("invalid_range_message", "Start date may not be later than End date");
            return "employees";
        }

        if(dateRange.isEmpty()){
            return "redirect:/employees/" + idDepartment;
        }

        for(Employee employee : employees){
            LocalDate birthDate = employee.getBirthDate();
            if(dateRange.isInsideRange(birthDate)){
                foundEmployees.add(employee);
            }
        }
        model.addAttribute("department", department);
        model.addAttribute("employees", foundEmployees);
        model.addAttribute("date_range", dateRange);
        model.addAttribute("search_message", "Search complete. Clear both Start Date and End Date to see the whole list");

        return "employees";
        //return "redirect:/employees/" + idDepartment;
    }
}
