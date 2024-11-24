package org.example.soutnance.service;


import org.example.soutnance.dto.request.ChampsRequest;
import org.example.soutnance.dto.response.ChampResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChampService {
    ChampResponse createChamp(ChampsRequest champsRequest);
    Page<ChampResponse> getChamps(Pageable pageable);
    ChampResponse getChamp(Long id);
    ChampResponse updateChamp(Long id, ChampsRequest champsRequest);
    void deleteChamp(Long id);
}
