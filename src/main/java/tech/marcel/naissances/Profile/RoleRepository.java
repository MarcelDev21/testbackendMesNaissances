package tech.marcel.naissances.Profile;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    //Optional<Role> findByName(String name);
    Optional<Role> findByName(String name);
}
