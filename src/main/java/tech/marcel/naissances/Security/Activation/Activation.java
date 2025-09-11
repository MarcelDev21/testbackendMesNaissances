package tech.marcel.naissances.Security.Activation;

import jakarta.persistence.*;
import lombok.*;
import tech.marcel.naissances.Profile.Profile;

import java.time.LocalDateTime;

@Entity
@Table(name = "activations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Activation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    @Transient
    private int codeClair;
    private LocalDateTime creation;
    private LocalDateTime desactivation;
    private Boolean active;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "profiles_id")
    private Profile profile;

}