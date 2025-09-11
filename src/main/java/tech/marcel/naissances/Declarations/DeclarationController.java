package tech.marcel.naissances.Declarations;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.marcel.naissances.Profile.ProfileDto;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("declarations")
@AllArgsConstructor

public class DeclarationController {

    private final DeclarationService declarationService;

    @PostMapping
    public void createDeclaration(@RequestBody Declaration declaration){
        this.declarationService.createDeclaration(declaration);
    }

    @GetMapping
    public List<DeclarationDto> declarationDto(){
      return this.declarationService.getAllDeclaration();
    }

    @PatchMapping(path = "{id}/status")
    public void updateStatus(@PathVariable int id, @RequestBody Map<String, String> parametre){
         this.declarationService.updateStatus(id, parametre);
    }
}