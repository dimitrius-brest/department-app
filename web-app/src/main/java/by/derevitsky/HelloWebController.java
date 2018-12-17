package by.derevitsky;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloWebController {
    @GetMapping
    public String printHelloMessage(ModelMap model){
        model.addAttribute("message", "Hello from Web App !");
        return "hello_web";
    }
}
