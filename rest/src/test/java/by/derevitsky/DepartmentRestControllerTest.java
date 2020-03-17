package by.derevitsky;

import by.derevitsky.model.Department;
import com.fasterxml.jackson.databind.ObjectMapper;

// --------- Junit 4 ---------
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;

// --------- Junit 5 ---------
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

// ---------  ---------
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
// ---------  ---------
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;          // for junit 5
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;      // for junit 4
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)              // junit 5
//@RunWith(SpringJUnit4ClassRunner.class)       // junit 4
//@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ConfigRest.class})
//@ContextConfiguration(locations = "file:rest/src/main/webapp/WEB-INF/old-department-rest-servlet.xml")
//@SpringJUnitWebConfig(classes = {WebConfig.class})
@ActiveProfiles(profiles = {"jdbc", "h2mem"})
public class DepartmentRestControllerTest {

    //@Autowired
    //private WebApplicationContext webApplicationContext;
    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    DepartmentRestController departmentRestController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        //this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(departmentRestController).build();
    }

    // -------- Tests -------------

    @Test
    public void testGetDepartment() throws Exception {
        Department mockDepartment = new Department(1, "Mock Department");
        Mockito
                .when(departmentService.getById(1))
                .thenReturn(mockDepartment);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/departments/1");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // --- Test null Department ---
        Mockito
                .when(departmentService.getById(2))
                .thenReturn(null);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/departments/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllDepartments() throws Exception {
        List<Department> mockDepartments = Arrays.asList(
                new Department(1, "Department 1"),
                new Department(2, "Department 2"),
                new Department(3, "Department 3")
        );
        Mockito
                .when(departmentService.getAll())
                .thenReturn(mockDepartments);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/departments/all");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // --- Test empty list of Departments ---
        mockDepartments = new ArrayList<>();
        Mockito
                .when(departmentService.getAll())
                .thenReturn(mockDepartments);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/departments/all"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSaveDepartment() throws Exception {
        Department mockDepartment = new Department(1, "Mock Department");
        ObjectMapper mapper = new ObjectMapper();
        //mapper.setSerializationInclusion(Inclusion.NON_NULL);
        byte[] serializedDepartment = mapper.writeValueAsBytes(mockDepartment);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/departments/add")
                .contentType("application/json")
                .content(serializedDepartment))
                    .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateDepartment() throws Exception {
        Department mockDepartment = new Department(1, "Updated Mock Department");
        ObjectMapper mapper = new ObjectMapper();
        byte[] serializedDepartment = mapper.writeValueAsBytes(mockDepartment);
        this.mockMvc.perform(MockMvcRequestBuilders.put("/departments/update")
                .contentType("application/json")
                .content(serializedDepartment))
                    .andExpect(status().isOk());
    }

    @Test
    public void testDeleteDepartment() throws Exception {
        Department mockDepartment = new Department(1, "Mock Department");
        Mockito
                .when(departmentService.getById(1))
                .thenReturn(mockDepartment);
        Mockito
                .when(departmentService.getById(2))
                .thenReturn(null);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/departments/1"))
                .andExpect(status().isNoContent());
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/departments/2"))
                .andExpect(status().isNotFound());
    }
}