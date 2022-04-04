package org.leti.Service;

import org.leti.Domain.Container;
import org.leti.Repo.ContainerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContainerService {
    @Autowired
    ContainerRepo containerRepo;

    public List<Container> getAllContainers() {
        return containerRepo.findAll();
    }
}
