package org.leti.Service;

import org.leti.Domain.Container;
import org.leti.Repo.ContainerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ContainerService {
    @Autowired
    ContainerRepo containerRepo;

    public List<Container> getAllContainers() {
        List<Container> allContainers = containerRepo.findAll();

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

        return sortedList;
    }

    @Scheduled( fixedDelay = 1000)
    public void increment() {
        List<Container> allContainers = containerRepo.findAll();

        for (Container cont : allContainers) {
            if (cont.getFullness() < 100) {
                if (Math.random() < cont.getPopularity()) {
                    cont.setFullness(cont.getFullness() + 1);
                    containerRepo.save(cont);
                }
            }
        }
    }
    public void fillDbWithContainers(int n) {
        //очистить базу контейнеров сначала
        containerRepo.deleteAll();

        //типы контейнера
        List<String> contTypes = new ArrayList<>();
        contTypes.add("Стекло");
        contTypes.add("Пластиковые бутылки и упаковки");
        contTypes.add("Бумага");
        contTypes.add("Картон, картонные упаковки");
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

        //Заполняем таблицу контейнеров
        for (int i = 0; i < n; i++) {
            Container cont = new Container();
            cont.setFullness(0);
            cont.setAddress( addresses.get((int) (Math.random()*185) ) );
            cont.setContainerType( contTypes.get((int) (Math.random()*4)));
            cont.setPopularity(Math.random());
            cont.setStatus("Работает");
            containerRepo.save(cont);
        }

    }
}
