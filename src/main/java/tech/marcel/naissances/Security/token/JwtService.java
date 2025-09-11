package tech.marcel.naissances.Security.token;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@AllArgsConstructor
@Component
public class JwtService {


    	/*<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
		</dependency>*/

    private final JwtEncoder jwtEncoder;

    // methode de géneration du token
    public String generate(Authentication authentication){
        Instant now = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet // JwtClaims est une classe de SpringSecurity qui te permet de definir les claims de ton token
                .builder()
                .issuedAt(now) // date d'emission du token
                .expiresAt(now.plus(1, ChronoUnit.HOURS)) // date d expiration
                .subject(authentication.getName()) //  l’utilisateur (souvent l’ID ou nom d'utilisateur).
                .claim("userName", authentication.getName()) // champ personnalisé (ici, userName).
                .issuer("self") // l’émetteur du token (peut être un nom de domaine ou ici "self").
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }


}
