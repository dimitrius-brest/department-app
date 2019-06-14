package by.derevitsky.web;

import by.derevitsky.Department;
import by.derevitsky.web.controller.DepartmentsWebController;
import by.derevitsky.web.model.DepartmentForView;
import by.derevitsky.web.service.DepartmentsWebService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.WebApplicationContext;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class DepartmentsWebControllerTest {

//    @Autowired
//    private WebApplicationContext webApplicationContext;

    @Mock
    private DepartmentsWebService webService;

    @InjectMocks
    DepartmentsWebController webController;

    private MockMvc mockMvc;

    @Before
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
