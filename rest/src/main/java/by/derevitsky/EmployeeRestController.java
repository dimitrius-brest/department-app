package by.derevitsky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Integer id){
        if(id == null) {
            return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
        }

        Employee employee = this.employeeService.getById(id);

        if (employee == null) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = this.employeeService.getAll();
        if (employees.isEmpty()) {
            return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<Employee> saveEmployee(@RequestBody /*@Valid*/ Employee employee) {
        HttpHeaders headers = new HttpHeaders();
        if (employee == null) {
            return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
        }
        this.employeeService.save(employee);
        return new ResponseEntity<Employee>(employee, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<Employee> updateEmployee(@RequestBody /*@Valid*/ Employee employee, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        if (employee == null) {
            return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
        }
        this.employeeService.save(employee);
        return new ResponseEntity<Employee>(employee, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Integer id) {
        Employee employee = this.employeeService.getById(id);
        if (employee == null) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        this.employeeService.delete(id);
        return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
    }
}
