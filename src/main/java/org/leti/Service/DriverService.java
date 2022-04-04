package org.leti.Service;

import org.leti.Domain.Container;
import org.leti.Domain.Driver;
import org.leti.Repo.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    @Autowired
    DriverRepo driverRepo;

    public List<Driver> getAllDrivers() {
        return driverRepo.findAll();
    }

}
