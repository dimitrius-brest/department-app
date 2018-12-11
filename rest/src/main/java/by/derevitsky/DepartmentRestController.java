package by.derevitsky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("departments")
public class DepartmentRestController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Department> getDepartment(@PathVariable("id") Integer id){
        if(id == null) { return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST); }
        Department department = this.departmentService.getById(id);
        if (department == null) { return new ResponseEntity<Department>(HttpStatus.NOT_FOUND); }
        return new ResponseEntity<Department>(department, HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = this.departmentService.getAll();
        if (departments.isEmpty()) {
            return new ResponseEntity<List<Department>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Department>>(departments, HttpStatus.OK);
    }

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<Department> saveDepartment(@RequestBody /*@Valid*/ Department department) {
        HttpHeaders headers = new HttpHeaders();
        if (department == null) {
            return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST);
        }
        this.departmentService.insert(department);
        return new ResponseEntity<Department>(department, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<Department> updateDepartment(@RequestBody /*@Valid*/ Department department, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        if (department == null) {
            return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST);
        }
        this.departmentService.update(department);
        return new ResponseEntity<Department>(department, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<Department> deleteDepartment(@PathVariable("id") Integer id) {
        Department department = this.departmentService.getById(id);
        if (department == null) {
            return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
        }
        this.departmentService.delete(id);
        return new ResponseEntity<Department>(HttpStatus.NO_CONTENT);
    }
}
