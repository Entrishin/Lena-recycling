package org.leti.Controller;

import org.leti.Domain.Container;
import org.leti.Service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class ContainerController {
    @Autowired
    ContainerService containerService;

    @GetMapping("/containers")
    public String containers(Map<String, Object> model) {
        List<Container> allContainers = containerService.getAllContainers();
        ArrayList<Long> contIds = new ArrayList<>();
        ArrayList<Container> sortedList = new ArrayList<>();


        for (Container cont : allContainers) {
            contIds.add(cont.getId());
        }
        Collections.sort(contIds);

        for (Long contId : contIds) {
            for (Container cont : allContainers) {
                if (cont.getId() == contId)
                    sortedList.add(cont);
            }
        }


        model.put("containers",sortedList);

        return "containers";
    }
}
