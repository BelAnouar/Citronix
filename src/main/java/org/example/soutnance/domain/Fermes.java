package org.example.soutnance.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fermes {
@Id
    private int id;
    private String nom;
    private String localisation;
    private String superficie;
    private LocalDate DateCreation;
}
