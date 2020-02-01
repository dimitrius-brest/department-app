package by.derevitsky;

import by.derevitsky.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RestConfig.class})
public class EmployeeRestControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    EmployeeRestController employeeRestController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeRestController).build();
    }

    // -------- Tests -------------

    @Test
    public void testGetEmployee() throws Exception {
        Employee mockEmployee = new Employee(1, 1, "Name", "Mock", "User",
                LocalDate.parse("1991-01-01"), 500);
        Mockito
                .when(employeeService.getById(1))
                .thenReturn(mockEmployee);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/employees/1");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // --- Test null Employee (NOT_FOUND) ---
        Mockito
                .when(employeeService.getById(2))
                .thenReturn(null);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employees/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        List<Employee> mockEmployees = Arrays.asList(
                new Employee(1, 1, "Name1", null, "User1",
                        LocalDate.parse("1991-01-01"), 1000),
                new Employee(2, 1, "Name2", null, "User2",
                        LocalDate.parse("1992-02-02"), 2000)
        );
        Mockito
                .when(employeeService.getAll())
                .thenReturn(mockEmployees);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/employees/all");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // --- Test empty list of Employees ---
        mockEmployees = new ArrayList<>();
        Mockito
                .when(employeeService.getAll())
                .thenReturn(mockEmployees);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employees/all"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetEmployeesByDepartmentId() throws Exception {
        List<Employee> mockEmployees = Arrays.asList(
                new Employee(1, 1, "Name1", null, "User1",
                        LocalDate.parse("1991-01-01"), 1000),
                new Employee(2, 1, "Name2", null, "User2",
                        LocalDate.parse("1992-02-02"), 2000)
        );
        Mockito
                .when(employeeService.getByDepartmentId(1))
                .thenReturn(mockEmployees);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/employees/dep/1");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // --- Test empty list of Employees ---
        mockEmployees = new ArrayList<>();
        Mockito
                .when(employeeService.getByDepartmentId(2))
                .thenReturn(mockEmployees);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employees/dep/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddEmployee() throws Exception {
        Employee mockEmployee = new Employee(1, 1, "Name", "Mock", "User",
                LocalDate.parse("1991-01-01"), 500);
        // --- serializing Employee and LocalDate ---
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());                                    // for LocalDate
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);   // for LocalDate
        byte[] serializedEmployee = mapper.writeValueAsBytes(mockEmployee);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/employees/add")
                .contentType("application/json")
                .content(serializedEmployee))
                    .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        Employee mockEmployee = new Employee(1, 1, "Updated", "Mock", "Employee",
                LocalDate.parse("1992-02-02"), 500);
        // --- serializing Employee and LocalDate ---
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());                                     // for LocalDate
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);    // for LocalDate
        byte[] serializedEmployee = mapper.writeValueAsBytes(mockEmployee);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/employees/update")
                .contentType("application/json")
                .content(serializedEmployee))
                    .andExpect(status().isOk());
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        Employee mockEmployee = new Employee(1, 1, "Name", "Mock", "User",
                LocalDate.parse("1991-01-01"), 500);
        Mockito
                .when(employeeService.getById(1))
                .thenReturn(mockEmployee);
        Mockito
                .when(employeeService.getById(2))
                .thenReturn(null);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/employees/1"))
                .andExpect(status().isNoContent());
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/employees/2"))
                .andExpect(status().isNotFound());
    }
}
