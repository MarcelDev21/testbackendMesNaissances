package tech.marcel.naissances.Shared.Esception.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.marcel.naissances.Shared.Esception.Entities.Status;

import java.util.Optional;

public interface StausRepository extends JpaRepository<Status,Integer> {
   Optional<Status> findByName(String name);
}
