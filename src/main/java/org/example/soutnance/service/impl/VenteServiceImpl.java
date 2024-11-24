package org.example.soutnance.service.impl;


import lombok.RequiredArgsConstructor;
import org.example.soutnance.dto.request.VentesRequest;
import org.example.soutnance.dto.response.VentesResponse;
import org.example.soutnance.repository.VenteRepository;
import org.example.soutnance.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class VenteServiceImpl implements VenteService {
    private final VenteRepository venteRepository;


    @Override
    public VentesResponse createVente(VentesRequest request) {
        return null;
    }

    @Override
    public Page<VentesResponse> getAllVentes(Pageable pageable) {
        return null;
    }

    @Override
    public VentesResponse getVente(Long id) {
        return null;
    }

    @Override
    public VentesResponse updateVente(Long id, VentesRequest request) {
        return null;
    }

    @Override
    public void deleteVente(Long id) {

    }
}
