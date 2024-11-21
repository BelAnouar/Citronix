package org.example.soutnance.service.impl;


import lombok.RequiredArgsConstructor;
import org.example.soutnance.domain.Fermes;
import org.example.soutnance.dto.request.FermesRequest;
import org.example.soutnance.dto.response.FermesResponse;
import org.example.soutnance.mapper.FermeMapper;
import org.example.soutnance.repository.FermeRepository;
import org.example.soutnance.service.FermeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FermesServiceImpl implements FermeService {

    private final FermeRepository fermeRepository;
    private final FermeMapper fermeMapper;
    @Override
    public FermesResponse createFermeRepository(FermesRequest fermesRequest) {

        Fermes ferme = fermeMapper.toEntity(fermesRequest);
         Fermes fermesave = fermeRepository.save(ferme);
        return fermeMapper.toResponse(fermesave);
    }

    @Override
    public Page<FermesResponse> findAllFermes(Pageable pageable) {
        return fermeRepository.findAll(pageable).map(fermeMapper::toResponse);
    }

    @Override
    public FermesResponse findFermeById(Long id) {
        return fermeRepository.findById(id).map(fermeMapper::toResponse).orElse(null);
    }

    @Override
    public FermesResponse deleteFermeById(Long id) {
        fermeRepository.deleteById(id);
        return null;
    }

    @Override
    public FermesResponse updateFermeById(Long id, FermesRequest fermesRequest) {
        Fermes ferme= fermeRepository.findById(id).orElse(null);
        fermeMapper.updateEntity(ferme, fermesRequest);
        Fermes saveFarme= fermeRepository.save(ferme);
        return fermeMapper.toResponse(saveFarme);
    }
}
