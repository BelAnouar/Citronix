package org.example.soutnance.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.soutnance.domain.Arbares;
import org.example.soutnance.dto.request.ArbaresRequest;
import org.example.soutnance.dto.response.ArbaresResponse;
import org.example.soutnance.mapper.ArbreMapper;
import org.example.soutnance.repository.ArbareRepository;
import org.example.soutnance.service.ArbreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArbreServiceImpl implements ArbreService {
    private final ArbareRepository arbareRepository;
    private final ArbreMapper  arbreMapper;

    @Override
    public ArbaresResponse createArbre(ArbaresRequest arbaresRequest) {
        Arbares arbares = arbreMapper.toEntity(arbaresRequest);
        Arbares savedArbare = arbareRepository.save(arbares);
        return arbreMapper.toResponse(savedArbare);
    }

    @Override
    public Page<ArbaresResponse> getArbres(Pageable pageable) {
        return arbareRepository.findAll(pageable).map(arbreMapper::toResponse);
    }

    @Override
    public ArbaresResponse getArbre(Long id) {
        return arbareRepository.findById(id).map(arbreMapper::toResponse).orElse(null);
    }

    @Override
    public ArbaresResponse updateArbre(Long id, ArbaresRequest arbaresRequest) {
        Arbares arbares=   arbareRepository.findById(id).orElse(null);
        arbreMapper.updateEntity(arbares, arbaresRequest);
        Arbares savedArbare = arbareRepository.save(arbares);
        return arbreMapper.toResponse(savedArbare) ;
    }
}
