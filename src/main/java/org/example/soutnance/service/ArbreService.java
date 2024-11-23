package org.example.soutnance.service;


import org.example.soutnance.dto.request.ArbaresRequest;
import org.example.soutnance.dto.response.ArbaresResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArbreService {

    ArbaresResponse createArbre(ArbaresRequest arbaresRequest);
    Page<ArbaresResponse> getArbres(Pageable pageable);
    ArbaresResponse getArbre(Long id);
    ArbaresResponse updateArbre(Long id, ArbaresRequest arbaresRequest);
}
