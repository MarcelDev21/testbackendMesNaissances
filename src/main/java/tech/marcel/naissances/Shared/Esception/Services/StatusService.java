package tech.marcel.naissances.Shared.Esception.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tech.marcel.naissances.Shared.Esception.Repositories.StausRepository;
import tech.marcel.naissances.Shared.Esception.Entities.Status;

import java.util.Map;

@AllArgsConstructor
@Component

public class StatusService {
    private final StausRepository stausRepository;

    public Status getStatus(Map<String,String> parametre){
        return this.stausRepository.findByName(parametre.get("name")).orElseThrow(()-> new RuntimeException("Pas Connue"));
    }
}