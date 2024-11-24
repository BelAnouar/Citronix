package org.example.soutnance.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecolteDetailResponse {
    private Long id;
    private Long arbre_id;
    private Double quantite;
    private Long recolte_id;
}
