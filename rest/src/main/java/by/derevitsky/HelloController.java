package by.derevitsky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {

    // How to get active profile?
    // See: https://stackoverflow.com/questions/9267799/how-do-you-get-current-active-default-environment-profile-programmatically-in-sp/13361783
    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String printHello(ModelMap model){
        model.addAttribute("message", "Hello from department-app!!!<br>Active profile: " + activeProfile);
        return "hello";
    }
}
