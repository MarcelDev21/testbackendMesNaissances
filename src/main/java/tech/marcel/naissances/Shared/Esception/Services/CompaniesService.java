package tech.marcel.naissances.Shared.Esception.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tech.marcel.naissances.Shared.Esception.Entities.Company;
import tech.marcel.naissances.Shared.Esception.Repositories.CompaniesRepository;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CompaniesService {

    private final CompaniesRepository companiesRepository;

    public Company VerifIfNameCompanieExist(Company company){
       Optional<Company> companyName =  this.companiesRepository.findByName(company.getName());
        return companyName.orElseGet(() -> this.companiesRepository.save(company));
    }
}