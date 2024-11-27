package org.example.soutnance.service.impl;


import lombok.RequiredArgsConstructor;
import org.example.soutnance.dto.request.VentesRequest;
import org.example.soutnance.dto.response.VentesResponse;
import org.example.soutnance.exception.ResourceNotFoundException;
import org.example.soutnance.mapper.VenteMapper;
import org.example.soutnance.repository.RecolteRepository;
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
    private final RecolteRepository recolteRepository;
    private final VenteMapper venteMapper;


    @Override
    public VentesResponse createVente(VentesRequest request) {

        var recolte = recolteRepository.findById(request.getRecolte_id())
                .orElseThrow(() -> new ResourceNotFoundException("Harvest not found with id: " + request.getRecolte_id()));


        var vente = venteMapper.toEntity(request);
        vente.setRecolte(recolte);
        vente = venteRepository.save(vente);

        return venteMapper.toResponse(vente);
    }

    @Override
    public Page<VentesResponse> getAllVentes(Pageable pageable) {
        return venteRepository.findAll(pageable)
                .map(venteMapper::toResponse);
    }

    @Override
    public VentesResponse getVente(Long id) {
        return venteRepository.findById(id)
                .map(venteMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found with id: " + id));
    }

    @Override
    public VentesResponse updateVente(Long id, VentesRequest request) {


        var vente = venteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found with id: " + id));


        if (!vente.getRecolte().getId().equals(request.getRecolte_id())) {
            recolteRepository.findById(request.getRecolte_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Harvest not found with id: " + request.getRecolte_id()));
        }

        venteMapper.updateEntity(vente, request);
        vente = venteRepository.save(vente);

        return venteMapper.toResponse(vente);
    }

    @Override
    public void deleteVente(Long id) {
        if (!venteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sale not found with id: " + id);
        }
        venteRepository.deleteById(id);
    }

}
