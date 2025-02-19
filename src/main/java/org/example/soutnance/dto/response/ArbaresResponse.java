package org.example.soutnance.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.soutnance.domain.Champs;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArbaresResponse {
    private Long id;
    private Champs champ;

    private int age;
    private LocalDate dateDeplantation;

}
