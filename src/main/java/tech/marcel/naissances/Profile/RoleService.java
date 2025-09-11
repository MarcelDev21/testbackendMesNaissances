package tech.marcel.naissances.Profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Getter
@Setter
public class RoleService {

    private final RoleRepository roleRepository;

    public Role VerificationRole(String Public){
        return this.roleRepository.findByName(Public)
                .orElseThrow(()-> new RuntimeException("Nous ne le trouvons pas"));

    }
}