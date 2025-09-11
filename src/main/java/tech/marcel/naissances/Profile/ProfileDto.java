package tech.marcel.naissances.Profile;

import lombok.*;
import org.springframework.stereotype.Component;
import tech.marcel.naissances.Shared.Esception.Entities.Address;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProfileDto {
    private int id;
    private String firstname;
    private String lastname;
    private Civility civility;
    private String email;
    private String phone;
    private LocalDateTime creation;
    private LocalDateTime birthDate;
    private Boolean active;
    private Address address;
    private String role;
}