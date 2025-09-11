package tech.marcel.naissances.Declarations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tech.marcel.naissances.Profile.ProfileMapping;

@Component
@AllArgsConstructor
public class DeclarationsMapper {

    private final ProfileMapping profileMapping;

    public DeclarationDto ObjetToDto(Declaration declaration){
        return DeclarationDto.builder()
                .id(declaration.getId())
                .name(declaration.getName())
                .description(declaration.getDescription())
                .registered(declaration.getRegistered())
                .child(this.profileMapping.mappingProfile(declaration.getChild()))
                .firstParent(this.profileMapping.mappingProfile(declaration.getFirstParent()))
                .secondParent(this.profileMapping.mappingProfile(declaration.getSecondParent()))
                .company(declaration.getCompany())
                .declarationStatus(declaration.getDeclarationStatus())
                .role(declaration.getRole())
                .build();
    }
}