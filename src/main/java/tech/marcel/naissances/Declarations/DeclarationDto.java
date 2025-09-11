package tech.marcel.naissances.Declarations;

import lombok.*;
import org.springframework.stereotype.Component;
import tech.marcel.naissances.Profile.Profile;
import tech.marcel.naissances.Profile.ProfileDto;
import tech.marcel.naissances.Profile.Role;
import tech.marcel.naissances.Shared.Esception.Entities.Company;
import tech.marcel.naissances.Shared.Esception.Entities.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DeclarationDto{
    private int id;
    private String name;
    private String description;
    private LocalDate registered;
    private ProfileDto child;
    private ProfileDto firstParent;
    private ProfileDto secondParent;
    private Role role;
    private Company company;
    private List<DeclarationStatus> declarationStatus;
}