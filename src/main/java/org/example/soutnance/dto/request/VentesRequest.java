package org.example.soutnance.dto.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.soutnance.domain.Recoltes;

import java.math.BigDecimal;
import java.time.LocalDate;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentesRequest {



    private Recoltes recolte_id;


    private LocalDate dateVente;


    private BigDecimal quantite;


    private BigDecimal prixUnitaire;


    private String nomClient;

}
