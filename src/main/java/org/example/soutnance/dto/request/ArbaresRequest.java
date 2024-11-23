package org.example.soutnance.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArbaresRequest {



   @NotNull(message = "data De plantation est")
    private LocalDate dateDeplantation;

    @NotNull(message = "champId est obligation")
    private Long champ_id;
}
