package org.example.soutnance.service;

import org.example.soutnance.dto.request.RecolteDetailRequest;
import org.example.soutnance.dto.response.RecolteDetailResponse;
import org.example.soutnance.dto.response.RecoltesResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecolteDetails {

     RecolteDetailResponse createRecolteDetail( RecolteDetailRequest request);
    Page<RecolteDetailResponse> getAllRecolteDetails(Pageable pageable);
    RecolteDetailResponse getRecolteDetails(Long id);
    RecolteDetailResponse updateRecolteDetails(Long id, RecolteDetailRequest recolteDetailRequest);
    void deleteRecolteDetails(Long id);
}
