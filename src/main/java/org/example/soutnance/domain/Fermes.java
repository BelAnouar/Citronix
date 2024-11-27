package org.example.soutnance.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.example.soutnance.domain.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Fermes extends BaseEntity {

    @Transient
    private  Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String localisation;

    @Column(nullable = false)
    @Min(value = 1000, message = "La superficie de la ferme doit être d'au moins 1000 mètres carrés")
    private Double superficieTotale;

    @Column(nullable = false)
    private LocalDate dateCreation;

    @OneToMany(mappedBy = "ferme", cascade = CascadeType.ALL)
    private List<Champs> champs = new ArrayList<>();



}
