package org.leti.Controller;

import org.leti.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class GreetingController {
    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }
}
