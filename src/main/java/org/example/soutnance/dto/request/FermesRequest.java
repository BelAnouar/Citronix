package org.example.soutnance.dto.request;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.example.soutnance.domain.Champs;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FermesRequest {

    @NotBlank(message ="le nom est obligatoire")
    private String nom;

    @NotBlank(message ="localisation est obligatoire")
    private String localisation;


    @NotNull(message = "superficieTotale est obligatoire")
    @Positive(message = "superficieTotale doit être positif")
    private Double superficieTotale;

    @NotNull(message = "dateCreation est obligatoire")
    private LocalDate dateCreation;

}
