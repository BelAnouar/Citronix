package org.example.soutnance.dto.response;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.soutnance.domain.Arbares;
import org.example.soutnance.domain.Fermes;
import org.example.soutnance.domain.Recoltes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChampResponse {
    private Long id;
    private BigDecimal superficie;
    private List<Arbares> arbres = new ArrayList<>();
    private List<Recoltes> recoltes = new ArrayList<>();
    private FermeResponse ferme;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FermeResponse {
        private Long id;
        private String nom;
        private String localisation;
        private BigDecimal superficieTotale;
        private LocalDate dateCreation;
    }



}
