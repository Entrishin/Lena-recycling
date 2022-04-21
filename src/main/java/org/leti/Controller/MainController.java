package org.leti.Controller;

import org.leti.Domain.Counterparty;
import org.leti.Service.CounterpartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    CounterpartyService counterpartyService;

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        //model.put("entries", entryRepo.findAll());
        return "main";
    }

    @GetMapping("/counterparty/{counterpartyName}")
    public String getCounterparties(Model model, @PathVariable String counterpartyName) {
        model.addAttribute("counterparties",counterpartyService.getAllCounterparties());

        Counterparty counterparty = counterpartyService.getByName(counterpartyName);

        if (counterparty != null)
            model.addAttribute("counterparty", counterparty); //ID контрагента по которому переходят на вкладку контрагентов, чтобы подсветить его зеленым цветом


        return "counterparty";
    }
/*
    private Long deleteWhiteSpaces(String s) {
        String s1 = s.replaceAll("\\s", "");
        byte[] bytes = s.getBytes();
        ArrayList<Byte> bytesList = new ArrayList<>();
        for (byte b : bytes) {
            if (b>0)
                bytesList.add(b);
        }

        byte[] bytes1 = new byte[bytesList.size()];
        for (int i = 0; i < bytesList.size(); i++) {
            bytes1[i] = bytesList.get(i);
        }
        String s2 = new String(bytes1, StandardCharsets.UTF_8);

        return Long.parseLong(s2);
    }

 */

}
