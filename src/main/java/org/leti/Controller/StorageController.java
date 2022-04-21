package org.leti.Controller;

import org.leti.Domain.Storage;
import org.leti.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class StorageController {
    @Autowired
    StorageService storageService;

    @GetMapping("/storages")
    public String storages(Map<String, Object> model) {
        List<Storage> storages = storageService.getAllWithoutDefault();
        model.put("storages", storages);
        model.put("uniqueStorages",storageService.getUniqueStorages());


        return "storages";
    }

}
