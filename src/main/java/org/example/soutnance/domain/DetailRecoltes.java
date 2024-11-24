package org.example.soutnance.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.soutnance.domain.base.BaseEntity;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailRecoltes extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arbre_id", nullable = false)
    private Arbares arbre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recolte_id", nullable = false)
    private Recoltes recolte;

    @Column(nullable = false)
    private Double quantite;
}
