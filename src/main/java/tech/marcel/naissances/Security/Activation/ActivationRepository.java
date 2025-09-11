package tech.marcel.naissances.Security.Activation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivationRepository extends JpaRepository<Activation, Integer> {
    List<Activation> findAllByActiveAndDesactivationAfter(Boolean active, LocalDateTime desactivation);
   // toutes les activations actives qui ne sont pas encore désactivées
}
