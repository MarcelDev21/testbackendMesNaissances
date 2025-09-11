package tech.marcel.naissances.Declarations;

import jakarta.persistence.*;
import lombok.*;
import tech.marcel.naissances.Profile.Profile;
import tech.marcel.naissances.Profile.Role;
import tech.marcel.naissances.Shared.Esception.Entities.Company;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "declarations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Declaration{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String comment;
    private LocalDate registered;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "child_id")
    private Profile child;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "first_parent_id")
    private Profile firstParent;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "second_parent_id")
    private Profile secondParent;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "roles_id")
    private Role role;

    @OneToMany(mappedBy = "declaration", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<DeclarationStatus> declarationStatus;

}