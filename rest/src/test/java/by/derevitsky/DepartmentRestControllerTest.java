package by.derevitsky;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
//@ContextConfiguration(locations = "file:rest/src/main/webapp/WEB-INF/old-department-rest-servlet.xml")
//@SpringJUnitWebConfig(classes = {WebConfig.class})
public class DepartmentRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        //mockMvc = MockMvcBuilders.standaloneSetup(new DepartmentRestController()).build();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testContextIsOk() throws Exception {
        ServletContext servletContext = webApplicationContext.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webApplicationContext.getBean("departmentRestController"));
    }

    @Test
    public void testHello() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/hello");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("hello"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("message"));
    }

    @Test
    public void testGetAll() throws Exception {

        //this.mockMvc.perform(get("/departments/all"))
        //        .andExpect(status().isOk());
                //.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/departments/all");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk());
                //.andDo(MockMvcResultHandlers.print())
                //.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON));
        //result.andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}