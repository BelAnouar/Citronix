package org.example.soutnance.domain;


import jakarta.persistence.*;
import lombok.*;
import org.example.soutnance.domain.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ventes extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recolte_id", nullable = false)
    private Recoltes recolte;

    @Column(nullable = false)
    private LocalDate dateVente;

    @Column(nullable = false)
    private Double quantite;

    @Column(nullable = false)
    private Double prixUnitaire;

    @Column(nullable = false)
    private String nomClient;

    public double calculerRevenu() {
        return quantite * prixUnitaire;
    }

}
