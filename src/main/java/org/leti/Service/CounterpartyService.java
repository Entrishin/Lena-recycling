package org.leti.Service;

import org.leti.Domain.Counterparty;
import org.leti.Repo.CounterpartyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CounterpartyService {
    @Autowired
    CounterpartyRepo counterpartyRepo;

    public List<Long> getAllIds(){
        List<Counterparty> counterparties = counterpartyRepo.findAll();
        ArrayList<Long> ids = new ArrayList<>();
        for (Counterparty counterparty : counterparties) {
            ids.add(counterparty.getId());
        }
        return ids;

    }

    public Counterparty getById(Long id) {
        return counterpartyRepo.findById(id).get();
    }

    public List<Counterparty> getAllCounterparties() {
        return counterpartyRepo.findAll();
    }

    public Counterparty getByName(String name) {
        return counterpartyRepo.findByName(name);
    }
}
