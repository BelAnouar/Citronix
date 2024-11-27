package org.example.soutnance.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentesResponse {


    private Long recolte_id;


    private LocalDate dateVente;


    private double quantite;


    private double prixUnitaire;


    private String nomClient;

}


