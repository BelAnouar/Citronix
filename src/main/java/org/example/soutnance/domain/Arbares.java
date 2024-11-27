package org.example.soutnance.domain;


import jakarta.persistence.*;
import lombok.*;
import org.example.soutnance.domain.base.BaseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Arbares extends BaseEntity {

    @Transient
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "champ_id", nullable = false)
    private Champs champ;

    @Column(nullable = false)
    private LocalDate dateDeplantation;

    @OneToMany(mappedBy = "arbre")
    private List<DetailRecoltes> detailsRecolte = new ArrayList<>();



}
