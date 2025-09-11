package tech.marcel.naissances.Shared.Esception.Service;


import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tech.marcel.naissances.Profile.Profile;
import tech.marcel.naissances.Profile.ProfileRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
@AllArgsConstructor
public class SecurityService {

    private final ProfileRepository profileRepository;

    public Profile getCurrentUser(){
        Jwt jwt = (Jwt) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String email = jwt.getSubject();

        Optional<Profile> profileOptional = this.profileRepository.findByEmail(email);
        return profileOptional.orElseThrow(()->new RuntimeException("Aucune entité de correspond aux paramètres fournis"));
    }
}