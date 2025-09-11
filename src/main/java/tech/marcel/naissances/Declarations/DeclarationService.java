package tech.marcel.naissances.Declarations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.marcel.naissances.Profile.*;
import tech.marcel.naissances.Shared.Esception.Entities.Company;
import tech.marcel.naissances.Shared.Esception.Repositories.StausRepository;
import tech.marcel.naissances.Shared.Esception.Service.SecurityService;
import tech.marcel.naissances.Shared.Esception.Services.CompaniesService;
import tech.marcel.naissances.Shared.Esception.Services.StatusService;
import tech.marcel.naissances.Shared.Esception.Entities.Status;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor

public class DeclarationService {

    private final DeclarationRepository declarationRepository;
    private final SecurityService securityService;
    private final ProfileService profileService;
    private final CompaniesService companiesService;
    private final RoleService roleService;
    private final StatusService statusService;
    private final DeclarationStatusRepository declarationStatusRepository;
    private final DeclarationsMapper declarationsMapper;
    private final StausRepository stausRepository;

    public void createDeclaration(Declaration declaration) {

        String name = declaration.getChild().getFirstname();
        declaration.setName("declaration de {}"+name);

        LocalDate localRegistered = LocalDate.now();
        declaration.setRegistered(localRegistered);

        Profile firstParent = this.securityService.getCurrentUser();
        declaration.setFirstParent(firstParent);

        Profile secondParent = this.profileService.verificationProfile(declaration.getSecondParent());
        declaration.setSecondParent(secondParent);

        Profile child = this.profileService.verificationProfileChild(declaration.getChild(), declaration.getChild().getBirthDate());
        declaration.setChild(child);


        Company company = this.companiesService.VerifIfNameCompanieExist(declaration.getCompany());
        declaration.setCompany(company);

        Role role = this.roleService.VerificationRole("Public");
        declaration.setRole(role);

        declaration = this.declarationRepository.save(declaration);

        Status status = this.statusService.getStatus(Map.of("name", "NEW"));
        DeclarationStatus declarationStatus = DeclarationStatus.builder()
                .creation(LocalDateTime.now())
                .status(status)
                .declaration(declaration)
                .build();

         this.declarationStatusRepository.save(declarationStatus);
    }

    public List<DeclarationDto> getAllDeclaration() {
        Profile profile = this.securityService.getCurrentUser();
        String email = profile.getEmail();
        String role = profile.getRole().getName();

        List<Declaration> declarationList;
        if(role.equals("Administrator") || role.equals("Public")){
            declarationList = this.declarationRepository.findAll();
        }else {
            declarationList =  this.declarationRepository.findCurrentUserDeclarations(email);
        }

        List<DeclarationDto> declarationDtos = new ArrayList<>();
        for(Declaration declaration : declarationList){
            declarationDtos.add(this.declarationsMapper.ObjetToDto(declaration));
        }
       return declarationDtos;

    }

    public void updateStatus(int id, Map<String, String> parametre) {
       Declaration declaration = this.declarationRepository.findById(id).orElseThrow(()->new RuntimeException("n existe pas"));
       Status status =this.stausRepository.findByName(parametre.get("name")).orElseThrow(()->new RuntimeException("ce status n existe pas"));
       DeclarationStatus declarationStatus = new DeclarationStatus();
       declarationStatus.setDeclaration(declaration);
       declarationStatus.setStatus(status);
       declarationStatus.setCreation(LocalDateTime.now());
       this.declarationStatusRepository.save(declarationStatus);
    }
}