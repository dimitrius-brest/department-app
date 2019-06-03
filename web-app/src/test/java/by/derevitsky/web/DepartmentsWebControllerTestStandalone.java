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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentsWebControllerTestStandalone {

    /**
     * See this: https://www.luckyryan.com/2013/08/24/unit-test-controllers-spring-mvc-test/
     */

    private MockMvc mockMvc;

    private List<DepartmentForView> mockDepartments = Arrays.asList(
            new DepartmentForView(new Department(1, "Mock Department 1"), 500, true),
            new DepartmentForView(new Department(2, "Mock Department 2"), 1000, true));

    @Mock
    private DepartmentsWebService departmentsWebService;

    @InjectMocks
    //@Autowired
    private DepartmentsWebController departmentsWebController;

    @Before
    public void setup() throws Exception {
        // Process Mock annotations
        MockitoAnnotations.initMocks(this);
        // Setup Spring test in Standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(departmentsWebController).build();
    }

    @Test
    public void testSomething() throws Exception {
        //Mockito.when(departmentsWebService.getDepartments()).thenReturn(mockDepartments);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/departments/update/1");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("department_update"));
                //.andExpect(MockMvcResultMatchers.model().attributeExists("department"));
    }
}
