package by.derevitsky;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    // Logging
    private static final Logger logger = Logger.getLogger(EmployeeRestController.class);

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Integer id){
        Employee employee = this.employeeService.getById(id);
        if (employee == null) {
            logger.debug("The Employee with id=" + id + " not found");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        logger.debug("The Employee with id=" + id + " and last name='" + employee.getLastName()+ "' was found");
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = this.employeeService.getAll();
        if (employees.isEmpty()) {
            logger.debug("The list of Employees is empty");
            return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
        }
        logger.debug("The list of all " + employees.size() + " Employees was got");
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping(value = "/dep/{dep_id}", produces = "application/json")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentId(@PathVariable("dep_id") Integer depId) {
        List<Employee> employees = this.employeeService.getByDepartmentId(depId);
        if (employees.isEmpty()) {
            logger.debug("The Department #" + depId + " has no Employees");
            return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
        }
        logger.debug("The list of " + employees.size() + " Employees in the Department #"
                 + depId + " was got");
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<Employee> addEmployee(@RequestBody /*@Valid*/ Employee employee) {
        HttpHeaders headers = new HttpHeaders();
        if (employee == null) {
            logger.debug("Failed to add a new Employee");
            return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
        }
        this.employeeService.insert(employee);
        logger.debug("New Employee with last name '" + employee.getLastName()
                   + "' was added to Department #" + employee.getIdDepartment());
        return new ResponseEntity<Employee>(employee, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<Employee> updateEmployee(@RequestBody /*@Valid*/ Employee employee, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        if (employee == null) {
            logger.debug("Failed to update the Employee");
            return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
        }
        this.employeeService.update(employee);
        logger.debug("The Employee with id=" + employee.getId() + " was successfully updated");
        return new ResponseEntity<Employee>(employee, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Integer id) {
        Employee employee = this.employeeService.getById(id);
        if (employee == null) {
            logger.debug("The Employee with id=" + id + " was not found");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        this.employeeService.delete(id);
        logger.debug("The Employee with id=" + id + " was successfully deleted");
        return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
    }
}
