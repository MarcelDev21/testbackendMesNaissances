package tech.marcel.naissances.Profile;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.marcel.naissances.Declarations.Declaration;
import tech.marcel.naissances.Shared.Esception.Service.SecurityService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapping profileMapping;
    private final SecurityService securityService;

    public List<ProfileDto> getAllProfiles() {
      List<Profile> tousMesProfiles =  this.profileRepository.findAll();
      List<ProfileDto> profileDtos = new ArrayList<>();

      for(Profile profile : tousMesProfiles){
          profileDtos.add(this.profileMapping.mappingProfile(profile));
      }
      return  profileDtos;
    }

    public ProfileDto getOneUser(int id) {
        Profile profile = this.profileRepository.findById(id).orElseThrow(()-> new RuntimeException("Cet identifiant n'est pas connu"));
        return this.profileMapping.mappingProfile(profile);
    }

    public ProfileDto updateProfile(int id, Profile profile) {
        Profile profileId = this.profileRepository.findById(id).orElseThrow(()-> new RuntimeException("Cet id est inexistant"));
        profileId.setFirstname(profile.getFirstname());
        profileId.setLastname(profile.getLastname());
        profileId.setCivility(profile.getCivility());
        profileId.setEmail(profile.getEmail());
        profileId.setPhone(profile.getPhone());
        profileId.setAddress(profile.getAddress());

        this.profileRepository.save(profileId);
        return this.profileMapping.mappingProfile(profileId);
    }

    public void deleteUser(int id) {
        this.profileRepository.deleteById(id);
    }

    public Profile verificationProfile(Profile secondParent) {
       /* Optional<Profile> profile =  this.profileRepository.findByEmail(secondParent.getEmail());
        return profile.orElseGet(() -> this.profileRepository.save(secondParent));*/

        /*Optional<Profile> profile =  this.profileRepository.findByEmail(secondParent.getEmail());
        if (profile.isEmpty()){
            return this.profileRepository.save(secondParent);
        }else {
            throw new RuntimeException("un enfant avec cet email est deja authentifié");
        }*/
        Optional<Profile> profile =  this.profileRepository.findByEmail(secondParent.getEmail());
        return profile.orElseGet(() -> this.profileRepository.save(secondParent));
    }


    public Profile verificationProfileChild(Profile profile, LocalDateTime localDateTime) {
       if(localDateTime == null){
           throw new RuntimeException("La date de naissance est oblogatoire");
       }else{
           return this.profileRepository.save(profile);
       }
    }


    public ProfileDto getCurrentUser() {
        Profile profile = this.securityService.getCurrentUser();
        return this.profileMapping.mappingProfile(profile);
    }
}