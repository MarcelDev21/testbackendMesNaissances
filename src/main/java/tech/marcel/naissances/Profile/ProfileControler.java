package tech.marcel.naissances.Profile;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("profiles")
@AllArgsConstructor

public class ProfileControler {

    private final ProfileService profileService;

    @GetMapping(path = "read")
    public ProfileDto read(){
        return this.profileService.getCurrentUser();
    }

    @GetMapping
    public List<ProfileDto> getAllProfiles(){
        return this.profileService.getAllProfiles();
    }

    @GetMapping(path = "{id}")
    public ProfileDto profileUser(@PathVariable int id){
        return this.profileService.getOneUser(id);
    }

    @PutMapping(path = "{id}")
    public ProfileDto updateProfile(@PathVariable int id, @RequestBody Profile profile){
        return this.profileService.updateProfile(id, profile);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable int id){
        this.profileService.deleteUser(id);
    }

}