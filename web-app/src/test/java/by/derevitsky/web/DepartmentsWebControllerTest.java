package by.derevitsky.web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
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

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class DepartmentsWebControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testContextIsOk() throws Exception {
        ServletContext servletContext = webApplicationContext.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webApplicationContext.getBean("departmentsWebController"));
    }

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
    public void testShowUpdateDepartmentForm() throws Exception {
        //TODO get Department from Rest
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/departments/update/1");
//        ResultActions result = mockMvc.perform(request);
//        result.andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("department_update"))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("department"));
    }
}
