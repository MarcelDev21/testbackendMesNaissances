package tech.marcel.naissances.Shared.Esception.Services;

import org.springframework.stereotype.Component;
import tech.marcel.naissances.Profile.Profile;
import tech.marcel.naissances.Profile.ProfileRepository;

import java.util.List;

@Component
public class ValidationService {

    public void VerifierMailExist(String email){
        if(email.isEmpty()){
             throw new RuntimeException("Votre Email est inexistant");
        }
    }

    public void verifierPhoneExist(String phone){
        if(phone.isEmpty()){
            throw new RuntimeException("Votre phone est inexistant");
        }
    }

    public void verifierSiMailIsPresent(List<Profile> all, String email) {
        for (Profile profile : all){
           if(profile.getEmail() != null){
               if(profile.getEmail().equals(email)){
                   throw new RuntimeException("Cet email existe deja !!!!");
               }
           }
        }
    }

    public void verifierSiPhoneIsPresent(List<Profile> all, String phone){
        for (Profile profile : all){
          if(profile.getPhone() != null){
              if(profile.getPhone().equals(phone)){
                  throw new RuntimeException("Ce phone existe deja !!!!");
              }
          }
        }
    }

    public void isActive(Boolean active) {
        if(active != null){  }
    }
}