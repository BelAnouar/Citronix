package org.example.soutnance.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.soutnance.domain.enums.Saison;

import java.time.LocalDate;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecoltesResponse {

    private LocalDate dateRecolte;

    private Saison saison;
    private Double quantite_totale;

    private Long champ_id;
}
