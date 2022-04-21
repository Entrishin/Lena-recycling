package org.leti.Repo;

import org.leti.Domain.Driver;
import org.leti.Domain.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepo extends JpaRepository<Storage, Long>  {
}
