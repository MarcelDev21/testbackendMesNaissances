package tech.marcel.naissances.Authentification;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.marcel.naissances.Profile.Profile;
import tech.marcel.naissances.Profile.ProfileDto;
import tech.marcel.naissances.Security.token.JwtService;

import java.util.Map;

@RestController
@AllArgsConstructor

public class AuthentificationController {

    private final AuthentificationService authentificationService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("sign-up")
    public ProfileDto createProfile(@RequestBody Profile profile){
        return this.authentificationService.createProfile(profile);
    }

    @PostMapping("activate")
    public void activation(@RequestBody Map<String, String> parametre){
         this.authentificationService.activation(parametre);
    }

    @PostMapping("sign-in")
    public Map<String, String> SignIn(@RequestBody Map<String, String> parametre){
        // ici on doit faire une verification par name et password ou alors on fait par email et on verifie si le password est egal a celui qu on a envoyé
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                      parametre.get("email"),
                        parametre.get("password")
                )
        );
        String bearer = this.jwtService.generate(authentication);
        return Map.of("bearer", bearer);
    }
}