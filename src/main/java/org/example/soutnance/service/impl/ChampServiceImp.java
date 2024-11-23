package org.example.soutnance.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.soutnance.domain.Champs;
import org.example.soutnance.dto.request.ChampsRequest;
import org.example.soutnance.dto.response.ChampResponse;
import org.example.soutnance.mapper.ChampMapper;
import org.example.soutnance.repository.ChampRepository;
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
    @Override
    public ChampResponse createChamp(ChampsRequest champsRequest) {

        Champs champs= champMapper.toEntity(champsRequest);
        Champs savedChamps = champRepository.save(champs);
        return champMapper.toResponse(savedChamps);
    }

    @Override
    public Page<ChampResponse> getChamps(Pageable pageable) {
        return champRepository.findAll(pageable).map(champMapper::toResponse);
    }

    @Override
    public ChampResponse getChamp(Long id) {
        return champRepository.findById(id).map(champMapper::toResponse).orElse(null);
    }

    @Override
    public ChampResponse updateChamp(Long id, ChampsRequest champsRequest) {
        Champs champs= champRepository.findById(id).orElse(null);
        champMapper.updateEntity(champs,champsRequest);

       Champs upadateChamp=  champRepository.save(champs);
        return champMapper.toResponse(upadateChamp);
    }

    @Override
    public ChampResponse deleteChamp(Long id) {

        champRepository.deleteById(id);
        return null;
    }
}
