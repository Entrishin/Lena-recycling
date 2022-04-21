package org.leti.Service;

import org.leti.Domain.Container;
import org.leti.Domain.Driver;
import org.leti.Domain.Storage;
import org.leti.Repo.StorageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class StorageService {
    @Autowired
    StorageRepo storageRepo;
    @Autowired
    CounterpartyService counterpartyService;
    @Autowired
    DriverService driverService;
    @Autowired
    ContainerService containerService;


    public List<Storage> getAll() {
        List<Storage> all = storageRepo.findAll();

        List<Long> listIds = new ArrayList<>();
        for (Storage storage : all) {
            listIds.add(storage.getId());
        }
        Collections.sort(listIds);

        ArrayList<Storage> result = new ArrayList<>();

        for (Long listId : listIds) {
            for (Storage storage : all) {
                if (storage.getId() == listId)
                    result.add(storage);
            }
        }
        return result;
    }

    public List<Storage> getAllWithoutDefault() {
        List<Storage> all = storageRepo.findAll();
        ArrayList<Storage> withoutDefault = new ArrayList<>();
        for (Storage storage : all) {
            if (storage.getStatus() == null )
                withoutDefault.add(storage);
        }

        List<Long> listIds = new ArrayList<>();
        for (Storage storage : withoutDefault) {
            listIds.add(storage.getId());
        }
        Collections.sort(listIds);

        ArrayList<Storage> result = new ArrayList<>();

        for (Long listId : listIds) {
            for (Storage storage : withoutDefault) {
                if (storage.getId() == listId)
                    result.add(storage);
            }
        }
        return result;
    }



    public void startStorageWorking(Container containerId, String storageAddress) {
        List<Storage> storages = storageRepo.findAll();
        Storage currentStorage = null;
        for (Storage storage : storages) {
            if (storage.getAddress().equals(storageAddress))
                currentStorage = storage;
        }
        if (currentStorage != null) {
            Storage newStorage = new Storage();
            newStorage.setCounterparty(currentStorage.getCounterparty());
            newStorage.setAddress(currentStorage.getAddress());
            newStorage.setExecution_recycling(0);
            newStorage.setContainer_id(containerId);
            storageRepo.save(newStorage);
        }




    }

    @Scheduled( fixedDelay = 2000)
    public void storageImitation() {
        List<Storage> storages = storageRepo.findAll();
        for (Storage storage : storages) {
            if (storage.getExecution_recycling() < 100) {
                double newVal = storage.getExecution_recycling() + (int) (Math.random() * 5);
                storage.setExecution_recycling(newVal>100?100:newVal);
                storageRepo.save(storage);
            }
        }
    }

    public void fillDbWithStorages() {
        //очистить базу складов сначала
        if (storageRepo.findAll().size() > 0)
            storageRepo.deleteAll();
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

        //Заполняем таблицу складов
        for (int i = 0; i < 3; i++) {
            Storage str = new Storage();
            str.setAddress(addresses.get((int) (Math.random() * 100)));
            str.setCounterparty(counterpartyService.getById(counterPartiesIds.get((int) (Math.random() * counterPartiesIds.size()))));
            str.setStatus("default");
            str.setExecution_recycling(0);

            storageRepo.save(str);
        }
    }

    public List<Storage> getUniqueStorages() {
        List<Storage> storages = storageRepo.findAll();
        ArrayList<Storage> result = new ArrayList<>();

        for (Storage storage : storages) {
            if (storage.getStatus() != null && storage.getStatus().equals("default"))
                result.add(storage);
        }

        return result;
    }
}
