package tech.marcel.naissances.Profile;

import org.springframework.stereotype.Component;

@Component
public class ProfileMapping {

    public ProfileDto mappingProfile(Profile profile){
        ProfileDto profileDto = ProfileDto.builder()
                .id(profile.getId())
                .firstname(profile.getFirstname())
                .lastname(profile.getLastname())
                .civility(profile.getCivility())
                .email(profile.getEmail())
                .phone(profile.getPhone())
                .address(profile.getAddress())
                .birthDate((profile.getBirthDate()))
                .active(profile.getActive())
                .build();

        if(profile.getRole() != null){
          profileDto.setRole(profile.getRole().getName());
        }

        return profileDto;
    }
}