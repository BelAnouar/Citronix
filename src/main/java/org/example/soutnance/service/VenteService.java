package org.example.soutnance.service;

import org.example.soutnance.dto.request.VentesRequest;
import org.example.soutnance.dto.response.VentesResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VenteService {

    VentesResponse createVente(VentesRequest request);
    Page<VentesResponse> getAllVentes(Pageable pageable);
    VentesResponse getVente(Long id);
    VentesResponse updateVente(Long id, VentesRequest request);
    void deleteVente(Long id);
}
