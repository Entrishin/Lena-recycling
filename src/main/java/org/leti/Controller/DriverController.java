package org.leti.Controller;

import org.leti.Domain.Driver;
import org.leti.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Map;

@Controller
public class DriverController {
    @Autowired
    DriverService driverService;

    @GetMapping("/drivers")
    public String drivers(Map<String, Object> model) {
        List<Driver> allDrivers = driverService.getAllDrivers();

        model.put("drivers",allDrivers);
        return "drivers";
    }
}
