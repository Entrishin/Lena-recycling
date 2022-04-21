package org.leti.Service;

import org.leti.Domain.Container;
import org.leti.Domain.Driver;
import org.leti.Domain.Storage;
import org.leti.Repo.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DriverService {
    @Autowired
    DriverRepo driverRepo;
    @Autowired
    StorageService storageService;
    @Autowired
    CounterpartyService counterpartyService;
    @Autowired
    ContainerService containerService;


    public List<Driver> getAllDrivers() {
        List<Driver> drivers = driverRepo.findAll();
        ArrayList<Driver> sortedList = new ArrayList<>();
        ArrayList<Long> ids = new ArrayList<>();
        for (Driver driver : drivers) {
            ids.add(driver.getId());
        }

        Collections.sort(ids);

        for (Long id : ids) {
            for (Driver driver : drivers) {
                if (driver.getId() == id)
                    sortedList.add(driver);
            }
        }



        return sortedList;

    }

    public void fillDbWithDrivers(int n) {
        //очистить базу контейнеров сначала
        driverRepo.deleteAll();
        //получить список складов
        List<Storage> storages = storageService.getAll();
        //получить список контрагентов
        List<Long> counterPartiesIds = counterpartyService.getAllIds();
        //берем рандомные адреса
        List<String> addresses = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/helper/addresses.txt"), "UTF8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                addresses.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Заполняем таблицу водителей
        for (int i = 0; i < n; i++) {
            Driver drv = new Driver();
            drv.setStatus("Ok");
            drv.setAdressTo(storages.get((int) (Math.random() * storages.size())).getAddress());
            drv.setCounterparty(counterpartyService.getById(counterPartiesIds.get((int) (Math.random() * counterPartiesIds.size()))));

            drv.setExecution_loading(0);
            drv.setExecution_ontheway(0);
            drv.setExecution_unloading(0);
            drv.setExecution_waytocontainer(0);
            drv.setName("name");

            driverRepo.save(drv);
        }

    }

    @Scheduled( fixedDelay = 5000)
    public void takeContainer() {

        List<Container> containers = containerService.getAllContainers();
        for (Container container : containers) {
            if (container.getFullness() > 90) {
                //присваиваем контейнер одному из водителей
                Driver freeDriver = getFreeDriver();
                if (freeDriver != null) {
                    freeDriver.setContainer_id(container);
                    freeDriver.setStatus("Working");
                    container.setFullness(0);
                    driverRepo.save(freeDriver);
                    containerService.updateContainer(container);
                }
            }
        }




    }


    @Scheduled( fixedDelay = 2000)
    public void driveImitation() {
        List<Driver> drivers = driverRepo.findAll();
        for (Driver driver : drivers) {
            if (driver.getStatus().equals("Working")) {
                driveAction(driver);
            }
        }

    }

    //увеличить процент движения по работе, при окончании все обнулить
    private void driveAction(Driver driver) {
        int val = (int) (Math.random() * 10); //0-9

        int toCont = (int) (driver.getExecution_waytocontainer()); //процент дороги до контейнера
        int toStorage = (int) (driver.getExecution_ontheway()); //процент дороги до склада
        int loading = (int) (driver.getExecution_loading()); // процент загрузки
        int unloading = (int) (driver.getExecution_unloading()); //процент разгрузки

        if (toCont < 100) {
            driver.setExecution_waytocontainer( (toCont + val) > 100 ? 100 : (toCont + val) );
        } else if (loading < 100) {
            driver.setExecution_loading( (loading + val) > 100 ? 100 : (loading + val) );
        } else if (toStorage < 100) {
            driver.setExecution_ontheway( (toStorage + val) > 100 ? 100 : (toStorage + val) );
        } else if (unloading < 100) {
            driver.setExecution_unloading( (unloading + val) > 100 ? 100 : (unloading + val) );
        } else { //все по 100 процент, значит водитель свободен
            driver.setExecution_unloading(0);
            driver.setExecution_ontheway(0);
            driver.setExecution_waytocontainer(0);
            driver.setExecution_loading(0);
            driver.setStatus("Ok");
            storageService.startStorageWorking(driver.getContainer_id(), driver.getAdressTo());
            driver.setContainer_id(null);

        }

        driverRepo.save(driver);

    }

    public Driver getFreeDriver() {
        List<Driver> drivers = driverRepo.findAll();
        for (Driver driver : drivers) {
            if (driver.getStatus().equals("Ok"))
                return driver;
        }
        return null;
    }

    public void save(Driver driver) {
        driverRepo.save(driver);
    }

}
