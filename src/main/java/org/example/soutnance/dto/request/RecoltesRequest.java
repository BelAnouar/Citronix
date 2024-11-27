package org.example.soutnance.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.soutnance.domain.enums.Saison;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecoltesRequest {

    private LocalDate dateRecolte;

    private Saison saison;
    private Double quantite_totale;

    private Long champ_id;

}
