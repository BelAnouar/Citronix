package org.example.soutnance.domain;


import jakarta.persistence.*;
import lombok.*;
import org.example.soutnance.domain.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fermes extends BaseEntity {


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    @Min(value = 1000, message = "Farm area must be at least 1000 square meters")
    private BigDecimal totalArea;

    @Column(nullable = false)
    private LocalDate creationDate;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<Champs> fields = new ArrayList<>();


}
