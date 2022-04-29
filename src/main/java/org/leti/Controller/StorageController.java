package org.leti.Controller;

import org.leti.Domain.Storage;
import org.leti.Service.StorageService;
import org.leti.Utils.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/doc_info/{id}")
    public String show_doc_3(Map<String,Object> map, @PathVariable String id) {

        map.put("raz",id);

        Map<String, String> docIntroMap = Map.copyOf(Json.getFileInfo(Long.parseLong(id)));

        for (Map.Entry<String,String> ent : docIntroMap.entrySet()) {
            map.put(ent.getKey(),ent.getValue());
        }

        return "doc_info";
    }

}
