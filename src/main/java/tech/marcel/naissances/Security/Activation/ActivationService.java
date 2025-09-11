package tech.marcel.naissances.Security.Activation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import tech.marcel.naissances.Profile.Profile;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@AllArgsConstructor
public class ActivationService {

    private final ActivationRepository activationRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Activation creerCodeActivation(Profile profile) {
        Random random = new Random();
        int userCode = 100000 + random.nextInt(900000);

        Activation activation = Activation.builder()
                .code(this.passwordEncoder.encode(""+userCode))
                .codeClair(userCode)
                .creation(LocalDateTime.now())
                .desactivation(LocalDateTime.now().plusMinutes(5))
                .active(Boolean.TRUE)
                .profile(profile)
                .build();
        return  this.activationRepository.save(activation);
    }
}