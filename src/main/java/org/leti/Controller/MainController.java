package org.leti.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {
    /*@Autowired
    EntryRepo entryRepo;*/

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        //model.put("entries", entryRepo.findAll());
        return "main";
    }

}
