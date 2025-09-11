package tech.marcel.naissances.Profile;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Optional<Profile> findByEmail(String username);
    Optional<Profile> findByFirstname(String firstname);
}
