package org.example.soutnance.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FermesResponse {

private Long id;
    private String nom;

    private String localisation;


    private Double superficieTotale;


    private LocalDate dateCreation;


    private  ChampsRes champs ;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ChampsRes {
        private Long id;
        private Double superficie;
    }
}
