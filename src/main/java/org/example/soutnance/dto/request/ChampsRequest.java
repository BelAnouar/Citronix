package org.example.soutnance.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChampsRequest {

    @NotNull(message = "la superficie est obligatoire")
    private BigDecimal superficie;


    @NotNull(message = "ferme_id est obligatoire")
    private Long ferme_id;
}
