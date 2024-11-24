package org.example.soutnance.service;

import org.example.soutnance.dto.request.FermesRequest;
import org.example.soutnance.dto.response.FermesResponse;
import org.example.soutnance.repository.FermeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FermeService {

    FermesResponse createFermeRepository(FermesRequest fermesRequest);
    Page<FermesResponse> findAllFermes(Pageable pageable);
    FermesResponse findFermeById(Long id);
    void deleteFermeById(Long id);
    FermesResponse updateFermeById(Long id, FermesRequest fermesRequest);
}
