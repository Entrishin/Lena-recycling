package org.leti.Controller;

import org.leti.Domain.Container;
import org.leti.Service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ContainerController {
    @Autowired
    ContainerService containerService;

    @GetMapping("/containers")
    public String containers(Map<String, Object> model) {
        List<Container> allContainers = containerService.getAllContainers();
        model.put("containers",allContainers);
        return "containers";
    }

    @PostMapping("/fillcontainers")
    public String fillContainers(@RequestParam String numOfContainers,
                                 @RequestParam String numOfDrivers) {
        containerService.fillDbWithContainers(Integer.parseInt(numOfContainers) );

        return "redirect:/containers";
    }
}
