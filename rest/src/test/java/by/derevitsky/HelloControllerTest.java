package by.derevitsky;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;          // for junit 5
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;      // for junit 4
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)              // junit 5
//@RunWith(SpringJUnit4ClassRunner.class)       // junit 4
@WebAppConfiguration
@ContextConfiguration(classes = {RestConfig.class})
@ActiveProfiles(profiles = {"jdbc", "h2mem"})
public class HelloControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    // -------- Tests -------------

    @Test
    public void testHello() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/hello");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("hello"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("message"));
    }
}
