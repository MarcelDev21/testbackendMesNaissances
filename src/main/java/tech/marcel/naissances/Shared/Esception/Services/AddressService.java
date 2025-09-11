package tech.marcel.naissances.Shared.Esception.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.marcel.naissances.Shared.Esception.Entities.AddresRepository;
import tech.marcel.naissances.Shared.Esception.Entities.Address;

@Service
@AllArgsConstructor

public class AddressService {

    private final AddresRepository addresRepository;

    public Address createAddress(Address address){
      return   this.addresRepository.save(address);
    }

}