package tech.marcel.naissances.Shared.Esception.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.marcel.naissances.Shared.Esception.Entities.Company;

import java.util.Optional;

public interface CompaniesRepository extends JpaRepository<Company, Integer> {
    Optional<Company> findByName(String name);
}
