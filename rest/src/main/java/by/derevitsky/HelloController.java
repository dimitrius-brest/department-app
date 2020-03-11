package by.derevitsky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {

    // How to get active profile?
    // See: https://stackoverflow.com/questions/9267799/how-do-you-get-current-active-default-environment-profile-programmatically-in-sp/13361783
    //@Value("${spring.profiles.active}")
    //private String activeProfile;
    @Autowired
    private Environment env;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String printHello(ModelMap model){
        String activeProfile        = env.getProperty("spring.profiles.active");
        //String fromPropertieFile    = env.getProperty("my.profiles");

        model.addAttribute("message",
                "Hello from department-app!!!"
                + "<br>spring.profiles.active: " + activeProfile
                + "<br>access.type: " + env.getProperty("access.type") + ", db.type: " + env.getProperty("db.type")
                + "<br><br>db.driver: " + env.getProperty("db.driver")
                + "<br>db.url: " + env.getProperty("db.url")
                + "<br>db.username: " + env.getProperty("db.username")
                + "<br>db.password: " + env.getProperty("db.password")
        );
        return "hello";
    }
}
