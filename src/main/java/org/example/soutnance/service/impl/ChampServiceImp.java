package org.example.soutnance.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.soutnance.domain.Champs;
import org.example.soutnance.domain.Fermes;
import org.example.soutnance.dto.request.ChampsRequest;
import org.example.soutnance.dto.response.ChampResponse;
import org.example.soutnance.exception.BusinessException;
import org.example.soutnance.exception.ResourceNotFoundException;
import org.example.soutnance.mapper.ChampMapper;
import org.example.soutnance.repository.ChampRepository;
import org.example.soutnance.repository.FermeRepository;
import org.example.soutnance.service.ChampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class ChampServiceImp implements ChampService {
    private final ChampRepository champRepository;
    private final ChampMapper champMapper;
    private final FermeRepository fermeRepository;

    @Override
    public ChampResponse createChamp(ChampsRequest champsRequest) {



        Fermes ferme = fermeRepository.findById(champsRequest.getFerme_id())
                .orElseThrow(() -> new ResourceNotFoundException("Farm not found with ID: " + champsRequest.getFerme_id()));




        Champs champs = champMapper.toEntity(champsRequest);
        champs.setFerme(ferme);
        Champs savedChamps = champRepository.save(champs);

        return champMapper.toResponse(savedChamps);
    }

    @Override
    public Page<ChampResponse> getChamps(Pageable pageable) {
        return champRepository.findAll(pageable).map(champMapper::toResponse);
    }

    @Override
    public ChampResponse getChamp(Long id) {
        return champRepository.findById(id)
                .map(champMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Field not found with ID: " + id));
    }

    @Override
    public ChampResponse updateChamp(Long id, ChampsRequest champsRequest) {



        Champs champs = champRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Field not found with ID: " + id));


        if (!champs.getSuperficie().equals(champsRequest.getSuperficie())) {
            validateFieldAreaConstraints(champs.getFerme().getId(), champsRequest.getSuperficie());
        }


        champMapper.updateEntity(champs, champsRequest);
        Champs updatedChamp = champRepository.save(champs);

        return champMapper.toResponse(updatedChamp);
    }

    @Override
    public void deleteChamp(Long id) {
        if (!champRepository.existsById(id)) {
            throw new ResourceNotFoundException("Field not found with ID: " + id);
        }
        champRepository.deleteById(id);
    }private void validateFieldAreaConstraints(Long fermeId, Double superficie) {
        boolean areaExceeded = champRepository.existsByFerme_IdAndSuperficieGreaterThan(fermeId, superficie);
        if (areaExceeded) {
            throw new BusinessException("The field area cannot exceed 50% of the total farm area.");
        }
    }
}