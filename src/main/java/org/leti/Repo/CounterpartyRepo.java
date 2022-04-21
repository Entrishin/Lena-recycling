package org.leti.Repo;

import org.leti.Domain.Counterparty;
import org.leti.Domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterpartyRepo extends JpaRepository<Counterparty, Long> {

    Counterparty findByName(String name);
}
