package tech.marcel.naissances.Authentification;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.marcel.naissances.Notification.EmailService;
import tech.marcel.naissances.Profile.*;
import tech.marcel.naissances.Security.Activation.Activation;
import tech.marcel.naissances.Security.Activation.ActivationRepository;
import tech.marcel.naissances.Security.Activation.ActivationService;
import tech.marcel.naissances.Shared.Esception.Entities.Address;
import tech.marcel.naissances.Shared.Esception.Services.AddressService;
import tech.marcel.naissances.Shared.Esception.Services.ValidationService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j

public class AuthentificationService implements UserDetailsService {

    private final ValidationService validationService;
    private final ProfileRepository profileRepository;
    private final AddressService addressService;
    private final ProfileMapping profileMapping;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ActivationService activationService;
    private final ActivationRepository activationRepository;
    private final EmailService emailService;

    public ProfileDto createProfile(Profile profile) {

        this.validationService.VerifierMailExist(profile.getEmail());
        this.validationService.verifierPhoneExist(profile.getPhone());
        this.validationService.verifierSiMailIsPresent(this.profileRepository.findAll(), profile.getEmail());
        this.validationService.verifierSiPhoneIsPresent(this.profileRepository.findAll(), profile.getPhone());
        this.validationService.isActive(profile.getActive());

        profile.setActive(Boolean.FALSE);

        profile.setPassword(this.passwordEncoder.encode(profile.getPassword()));

        Address address = this.addressService.createAddress(profile.getAddress());
        profile.setAddress(address);

        Role role = this.roleRepository.findByName("Public")
                .orElseThrow(()-> new RuntimeException("Nous ne reconnaissons pas ce role"));
        profile.setRole(role);

        Profile profile1 = this.profileRepository.save(profile);

       Activation activation = this.activationService.creerCodeActivation(profile1);
       log.info(STR."Votre code d activation est \{activation.getCodeClair()}");

        this.emailService.EnvoieMail(
                Map.of(
                        "name", profile.getFirstname(),
                        "email", profile.getEmail(),
                        "code", ""+activation.getCodeClair(),
                        "template", "Activation-code.ftl"
                )
        );


        return this.profileMapping.mappingProfile(profile1);
    }

    public void activation(Map<String, String> parametre) {
        List<Activation> toutesMesPresentesActivations =
                this.activationRepository.findAllByActiveAndDesactivationAfter(Boolean.TRUE, LocalDateTime.now());

        List<Activation> activations = new ArrayList<>();
        for(Activation activation : toutesMesPresentesActivations){
            if(this.passwordEncoder.matches(parametre.get("code"), activation.getCode())){
                activations.add(activation);
            }
        }
        toutesMesPresentesActivations = activations;
        if (toutesMesPresentesActivations.isEmpty()){
            throw new RuntimeException("Votre Code n'exite pas");
        }

        Activation activation = toutesMesPresentesActivations.getFirst();
        activation.setActive(Boolean.FALSE);
        this.activationRepository.save(activation);

        Profile profile = activation.getProfile();
        profile.setActive(true);
        this.profileRepository.save(profile);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.profileRepository.findByEmail(username).orElseThrow(()-> new RuntimeException("email pas present"));
    }
}