package tech.marcel.naissances.Declarations;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import tech.marcel.naissances.Profile.Profile;
import tech.marcel.naissances.Shared.Esception.Entities.Status;

import java.time.LocalDateTime;

@Entity
@Table(name = "declarations_status")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class DeclarationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comment;
    private LocalDateTime creation;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "status_id")
    private Status status;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "declarations_id")
    private Declaration declaration;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "agents_id")
    private Profile agent;
}