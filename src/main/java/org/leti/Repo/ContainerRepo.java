package org.leti.Repo;

import org.leti.Domain.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContainerRepo extends JpaRepository<Container,Long> {

    @Query("SELECT t FROM Container t")
    List<Container> findAll();

}
