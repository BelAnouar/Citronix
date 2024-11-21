package org.example.soutnance.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.soutnance.domain.Champs;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FermesResponse {

private Long id;
    private String nom;

    private String localisation;


    private BigDecimal superficieTotale;


    private LocalDate dateCreation;


    private List<Champs> champs = new ArrayList<>();
}
