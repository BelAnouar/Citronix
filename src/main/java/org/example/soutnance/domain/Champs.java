package org.example.soutnance.domain;


import jakarta.persistence.*;
import lombok.*;
import org.example.soutnance.domain.base.BaseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Champs extends BaseEntity {



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private Fermes farm;

    @Column(nullable = false)
    @Min(value = 1000, message = "Field area must be at least 1000 square meters")
    private BigDecimal area;




}
