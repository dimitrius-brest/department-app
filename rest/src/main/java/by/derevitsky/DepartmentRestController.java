package by.derevitsky;

import by.derevitsky.model.Department;
import org.apache.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("departments")
public class DepartmentRestController {

    // How to get active profile?
    // See: https://stackoverflow.com/questions/9267799/how-do-you-get-current-active-default-environment-profile-programmatically-in-sp/13361783
    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Autowired
    private DepartmentService departmentService;

    // Logging
    private static final Logger logger = Logger.getLogger(DepartmentRestController.class);
    //private static final Logger logger = LogManager.getLogger(DepartmentRestController.class);

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Department> getDepartment(@PathVariable("id") Integer id){
        Department department = this.departmentService.getById(id);
        if (department == null) {
            logger.debug("The Department with id=" + id + " not found");
            return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
        }
        logger.debug("The Department with id=" + id + " and name='" + department.getName() + "' was found");

        //department.setName(department.getName() + " " + activeProfile);
        return new ResponseEntity<Department>(department, HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = this.departmentService.getAll();
        if (departments.isEmpty()) {
            logger.debug("The list of Departments is empty");
            return new ResponseEntity<List<Department>>(HttpStatus.NOT_FOUND);
        }
        logger.debug("The list of all " + departments.size() + " Departments was got");
        return new ResponseEntity<List<Department>>(departments, HttpStatus.OK);
    }

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<Department> saveDepartment(@RequestBody /*@Valid*/ Department department) {
        HttpHeaders headers = new HttpHeaders();
//        if (department == null) {
//            logger.debug("Failed to add a new Department");
//            return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST);
//        }
        this.departmentService.insert(department);
        logger.debug("New Department with name='" + department.getName() + "' was added");
        return new ResponseEntity<Department>(department, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<Department> updateDepartment(@RequestBody /*@Valid*/ Department department, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
//        if (department == null) {
//            logger.debug("Failed to update the Department");
//            return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST);
//        }
        this.departmentService.update(department);
        logger.debug("The Department with id=" + department.getId() + " was successfully updated");
        return new ResponseEntity<Department>(department, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<Department> deleteDepartment(@PathVariable("id") Integer id) {
        Department department = this.departmentService.getById(id);
        if (department == null) {
            logger.debug("The Department with id=" + id + " was not found");
            return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
        }
        this.departmentService.delete(id);
        logger.debug("The Department with id=" + id + " was successfully deleted");
        return new ResponseEntity<Department>(HttpStatus.NO_CONTENT);
    }
}
