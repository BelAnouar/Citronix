package org.example.soutnance.domain;


import jakarta.persistence.*;
import lombok.*;
import org.example.soutnance.domain.base.BaseEntity;
import org.example.soutnance.domain.enums.Saison;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recoltes extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "champ_id", nullable = false)
    private Champs champ;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Saison saison;

    @Column(nullable = false)
    private LocalDate dateRecolte;

    @Column(nullable = false)
    private BigDecimal quantiteTotale;

    @OneToMany(mappedBy = "recolte", cascade = CascadeType.ALL)
    private List<DetailRecoltes> detailsRecolte = new ArrayList<>();

    @OneToMany(mappedBy = "recolte", cascade = CascadeType.ALL)
    private List<Ventes> ventes = new ArrayList<>();
}
