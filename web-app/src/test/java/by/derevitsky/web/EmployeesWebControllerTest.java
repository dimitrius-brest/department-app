package by.derevitsky.web;

import by.derevitsky.Department;
import by.derevitsky.Employee;
import by.derevitsky.web.controller.EmployeesWebController;
import by.derevitsky.web.model.DepartmentForView;
import by.derevitsky.web.service.DepartmentsWebService;
import by.derevitsky.web.service.EmployeesWebService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class EmployeesWebControllerTest {

//    @Autowired
//    private WebApplicationContext webApplicationContext;
    @Mock
    private EmployeesWebService empWebService;
    @Mock
    private DepartmentsWebService depWebService;

    @InjectMocks
    EmployeesWebController empWebController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        //this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(empWebController).build();
    }

    // -------- Tests -------------

    @Test
    public void testShowEmployees() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/employees/all");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("employees"));
    }

    @Test
    public void testShowEmployeesByDepartmentId() throws Exception {
        Department mockDepartment = new Department(1, "Mock Department");
        List<Employee> mockEmployees = Arrays.asList(
                new Employee(1, 1, "Name1", null, "User1",
                        LocalDate.parse("1991-01-01"), 1000),
                new Employee(2, 1, "Name2", null, "User2",
                        LocalDate.parse("1992-02-02"), 2000)
        );
        Mockito
                .when(empWebService.getEmployeesByDepartmentId(1))
                .thenReturn(mockEmployees);
        Mockito
                .when(depWebService.getDepartmentById(1))
                .thenReturn(mockDepartment);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/employees/1");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("employees"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("employees"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("department"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("date_range"));
    }

    @Test
    public void testShowAddEmployeeForm() throws Exception {
        Department mockDepartment = new Department(1, "Mock Department");
        Mockito
                .when(depWebService.getDepartmentById(1))
                .thenReturn(mockDepartment);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/employees/add/1");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("employee_add"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("employee"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("department"));
    }

    @Test
    public void testAddEmployee() throws Exception {
        Department mockDepartment = new Department(1, "Mock Department");
        Employee mockEmployee = new Employee(1, 1, "Name", "Mock", "User",
                LocalDate.parse("1991-01-01"), 500);
        Mockito
                .when(depWebService.getDepartmentById(1))
                .thenReturn(mockDepartment);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/employees/add/1")
                .content(String.valueOf(mockEmployee));
        ResultActions result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.redirectedUrl("/employees/1"));

//        request = MockMvcRequestBuilders.post("/employees/add/1")
//                .param("lastName", "");
//        result = mockMvc.perform(request);
//        result.andExpect(MockMvcResultMatchers.redirectedUrl("/employees/1"));
    }

    @Test
    public void testShowUpdateEmployeeForm() throws Exception {
        Department mockDepartment = new Department(1, "Department 1");
        Employee mockEmployee = new Employee(1, 1, "Name", "Mock", "User",
                LocalDate.parse("1991-01-01"), 500);
        List<DepartmentForView> mockDepartments = Arrays.asList(
                new DepartmentForView(new Department(1, "Department 1"), 500, true),
                new DepartmentForView(new Department(2, "Department 2"), 1500, true)
        );

        Mockito
                .when(depWebService.getDepartmentById(1))
                .thenReturn(mockDepartment);
        Mockito
                .when(depWebService.getDepartments())
                .thenReturn(mockDepartments);
        Mockito
                .when(empWebService.getEmployeeById(1))
                .thenReturn(mockEmployee);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/employees/update/1");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("employee_update"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("employee"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("department"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("departments"));
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        Employee mockEmployee = new Employee(1, 1, "Name", "Mock", "User",
                LocalDate.parse("1991-01-01"), 500);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/employees/update")
                .content(String.valueOf(mockEmployee));
        ResultActions result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.redirectedUrl("/employees/0"));
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        Employee mockEmployee = new Employee(1, 1, "Name", "Mock", "User",
                LocalDate.parse("1991-01-01"), 500);
        Mockito
                .when(empWebService.getEmployeeById(1))
                .thenReturn(mockEmployee);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/employees/delete/1");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.redirectedUrl("/employees/1"));
    }

    @Test
    public void testSearchEmployees() throws Exception {
        Department mockDepartment = new Department(1, "Department 1");
        List<Employee> mockEmployees = Arrays.asList(
                new Employee(1, 1, "Name1", null, "User1",
                        LocalDate.parse("1991-01-01"), 1000),
                new Employee(2, 1, "Name2", null, "User2",
                        LocalDate.parse("1992-02-02"), 2000)
        );
        Mockito
                .when(depWebService.getDepartmentById(1))
                .thenReturn(mockDepartment);
        Mockito
                .when(empWebService.getEmployeesByDepartmentId(1))
                .thenReturn(mockEmployees);

        // add DateRange
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/search/1");
//        ResultActions result = mockMvc.perform(request);
//        result.andExpect(MockMvcResultMatchers.view().name("employees"));
    }
}
