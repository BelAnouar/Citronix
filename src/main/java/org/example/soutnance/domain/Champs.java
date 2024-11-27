package org.example.soutnance.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.example.soutnance.domain.base.BaseEntity;


import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Champs extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ferme_id", nullable = false)
    private Fermes ferme;

    @Column(nullable = false)
    @Min(value = 1000, message = "La superficie du champ doit être d'au moins 1000 mètres carrés")
    private Double superficie;

    @OneToMany(mappedBy = "champ", cascade = CascadeType.ALL)
    private List<Arbares> arbres = new ArrayList<>();

    @OneToMany(mappedBy = "champ", cascade = CascadeType.ALL)
    private List<Recoltes> recoltes = new ArrayList<>();





}
