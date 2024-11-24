package org.example.soutnance.service;


import org.example.soutnance.dto.request.RecoltesRequest;
import org.example.soutnance.dto.response.RecoltesResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecolteService {

    RecoltesResponse createRecolte(RecoltesRequest request);
    Page<RecoltesResponse> getRecoltes(Pageable pageable);
    RecoltesResponse getRecolte(Long id);
    RecoltesResponse updateRecolte(Long id, RecoltesRequest request);
    void deleteRecolte(Long id);
}
