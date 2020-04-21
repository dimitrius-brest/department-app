package by.derevitsky.web;

import by.derevitsky.model.Department;
import by.derevitsky.web.controller.DepartmentsWebController;
import by.derevitsky.web.service.DepartmentsWebService;

// --------- Junit 4 ---------
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;

// --------- Junit 5 ---------
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

// ---------  ---------
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;      // junit 5
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith(SpringExtension.class)              // junit 5
//@RunWith(SpringJUnit4ClassRunner.class)       // junit 4
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
//@ActiveProfiles("jsp")
public class DepartmentsWebControllerTest {

//    @Autowired
//    private WebApplicationContext webApplicationContext;

    @Mock
    private DepartmentsWebService webService;

    @InjectMocks
    DepartmentsWebController webController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        //this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(webController).build();
    }

    // -------- Tests -------------

    @Test
    public void testRedirectFromRoot() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/departments/");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.redirectedUrl("/departments/all"));
    }

    @Test
    public void testShowDepartments() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/departments/all");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("departments"));
    }

    @Test
    public void testShowAddDepartmentForm() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/departments/add");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("department_add"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("department"));
    }

    @Test
    public void testAddDepartment() throws Exception {
        Department department = new Department(1, "New Department");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/departments/add")
                .flashAttr("department", department);
        ResultActions result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.redirectedUrl("/departments/all"));

        // --- Test invalid length of "name"
        //String tooLongName = new String(new char[110]).replace("\0", "N");
        //department.setName(tooLongName);
//        department.setName("");
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/departments/add")
//                .flashAttr("department", department)
//                .param("name", ""))
//                    .andExpect(MockMvcResultMatchers.view().name("department_update"));
    }

    @Test
    public void testShowUpdateDepartmentForm() throws Exception {
        Department mockDepartment = new Department(1, "Mock Department");
        when(webService.getDepartmentById(1))
                .thenReturn(mockDepartment);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/departments/update/1");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("department_update"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("department"));
    }

    @Test
    public void testUpdateDepartment() throws Exception {
        Department department = new Department(1, "Updated Department");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/departments/update")
                .flashAttr("department", department);
        ResultActions result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.redirectedUrl("/departments/all"));
    }

    @Test
    public void testDeleteDepartment() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/departments/delete/1");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.redirectedUrl("/departments/all"));
    }
}
