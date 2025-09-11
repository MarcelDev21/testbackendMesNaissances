package tech.marcel.naissances.Security;


import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tech.marcel.naissances.Authentification.AuthentificationService;


@EnableWebSecurity
@Configuration
@AllArgsConstructor


public class ApplicationSecurity {


    private final RsaKeys rsaKeys;
    private BCryptPasswordEncoder passwordEncoder;
    private AuthentificationService authentificationService;




   /*public ApplicationSecurity(RsaKeys rsaKeys, BCryptPasswordEncoder passwordEncoder, AuthentificationService authentificationService) {
       this.rsaKeys = rsaKeys;
       this.passwordEncoder = passwordEncoder;
       this.authentificationService = authentificationService;
   }*/




    // Configuration de la sécurité web de ton app grace a SpringSecurity
    // SecurityFilterChain est l'objet qui intercepte les requetes toutes les requetes Http
    // HttpSecurity est l'objet de configuration de SpringSecurity pour les requetes
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return
                httpSecurity
                        .cors(Customizer.withDefaults()) // Permet au requetes venant d'autres orgines (Frontend) d'acceder a ton Application
                        .csrf(AbstractHttpConfigurer::disable)
                        .headers(AbstractHttpConfigurer::disable) // pour avoir le control total sur les entetes Http parceque SpringSecurity en a par defaut
                        .authorizeHttpRequests(
                                customizer ->
                                        customizer
                                                .requestMatchers(HttpMethod.POST,"/sign-in").permitAll()
                                                .requestMatchers(HttpMethod.POST,"/sign-up").permitAll()
                                                .requestMatchers(HttpMethod.POST,"/activate").permitAll() // RequestMatchers sont des chemins publics ou on a pas besoin d'être authentifié
                                                //  .requestMatchers(HttpMethod.PATCH,"/declarations/*/status").permitAll() // RequestMatchers sont des chemins publics ou on a pas besoin d'être authentifié


                                                .anyRequest().authenticated() // toutes les autres routes ou requêtes necessitent une authentification Jwt
                        )
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // L’utilisateur doit fournir un token JWT à chaque requête.
                        .oauth2ResourceServer(oauth ->oauth.jwt(Customizer.withDefaults())) // active le mode Api sécurisé par Token
                        .build(); // retourne l'objet Security FilterChain
    }


    // Comment il fera pour authentifier et verifier un utilisateur ici il verifie les identifiants d un user quand il se connecte
    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(); // IL PROVIENT DE LA BD ou il charge les utilisateurs de la Bd et Implemente UserDetailService
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder); // le PasswordEncoder
        daoAuthenticationProvider.setUserDetailsService(this.authentificationService); // permet de retrouver l utilisateur de la Bd
        return  daoAuthenticationProvider;
    }


    // Permet a Spring de Gerer un gestionnaire d'authentification personalisé et l utilise a chaque tentativ de connexion
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class); // il y est deja par defaut
        authenticationManagerBuilder.authenticationProvider(this.authenticationProvider());
        return authenticationManagerBuilder.build();
    }


    // JWTDecoder decode et valide un Jwt lorsque l utilisateur fait une requete d'authentification
    @Bean
    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(this.rsaKeys.publicKey()).build(); // on decode le Jwt grace a la clé public fournie il va aussi extraire le claims (contenu du token : sub, username, roles, etc.)
    }


    // utilisé pour créer (encoder) des JSON Web Tokens (JWT)
    @Bean
    JwtEncoder jwtEncoder(){
        final JWK jwk = new RSAKey // ici tu construis un json Web Key avec la clé privée et publique
                .Builder(this.rsaKeys.publicKey())
                .privateKey(this.rsaKeys.privateKey())
                .build();
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource); // on crée un encoder Jwt basé sur jwk
    }
}
