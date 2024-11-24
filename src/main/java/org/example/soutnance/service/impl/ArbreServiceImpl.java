package org.example.soutnance.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.soutnance.domain.Arbares;
import org.example.soutnance.domain.Champs;
import org.example.soutnance.dto.request.ArbaresRequest;
import org.example.soutnance.dto.response.ArbaresResponse;
import org.example.soutnance.exception.BusinessException;
import org.example.soutnance.exception.ResourceNotFoundException;
import org.example.soutnance.mapper.ArbreMapper;
import org.example.soutnance.repository.ArbareRepository;
import org.example.soutnance.repository.ChampRepository;
import org.example.soutnance.service.ArbreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArbreServiceImpl implements ArbreService {
    private final ArbareRepository arbreRepository;
    private final ChampRepository champRepository;
    private final ArbreMapper arbreMapper;

    @Override
    public ArbaresResponse createArbre(ArbaresRequest arbaresRequest) {

        Champs champ = champRepository.findById(arbaresRequest.getChamp_id())
                .orElseThrow(() -> new ResourceNotFoundException("Champ id" + arbaresRequest.getChamp_id()));

        validatePlantingPeriod(arbaresRequest.getDateDeplantation());

        validateTreeDensity(champ);

        Arbares arbre = arbreMapper.toEntity(arbaresRequest);
        validateTreeAge(arbre.getDateDeplantation());

        Arbares savedArbre = arbreRepository.save(arbre);
        return arbreMapper.toResponse(savedArbre);
    }

    @Override
    public Page<ArbaresResponse> getArbres(Pageable pageable) {
        return arbreRepository.findAll(pageable)
                .map(arbreMapper::toResponse);
    }

    @Override
    public ArbaresResponse getArbre(Long id) {
        return arbreRepository.findById(id)
                .map(arbreMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Arbre id"+String.valueOf(id)));
    }

    @Override
    public ArbaresResponse updateArbre(Long id, ArbaresRequest arbaresRequest) {
        Arbares arbre = arbreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arbre id" + id));

        if (arbaresRequest.getDateDeplantation() != null) {
            validatePlantingPeriod(arbaresRequest.getDateDeplantation());
        }

        if (arbaresRequest.getChamp_id() != null &&
                !arbaresRequest.getChamp_id().equals(arbre.getChamp().getId())) {
            Champs newChamp = champRepository.findById(arbaresRequest.getChamp_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Champ id"+ arbaresRequest.getChamp_id()));
            validateTreeDensity(newChamp);
        }

        arbreMapper.updateEntity(arbre, arbaresRequest);
        Arbares savedArbre = arbreRepository.save(arbre);
        return arbreMapper.toResponse(savedArbre);
    }

    private void validatePlantingPeriod(LocalDate plantingDate) {
        Month plantingMonth = plantingDate.getMonth();
        if (plantingMonth.getValue() < Month.MARCH.getValue() ||
                plantingMonth.getValue() > Month.MAY.getValue()) {
            throw new BusinessException("Les arbres ne peuvent être plantés qu'entre mars et mai");
        }
    }

    private void validateTreeDensity(Champs champ) {
        double champArea = champ.getSuperficie();
        long currentTreeCount = arbreRepository.countByChamp(champ);


        if (currentTreeCount >= champArea * 100) {
            throw new BusinessException(
                    String.format("Densité maximale d'arbres dépassée. Maximum autorisé: %d arbres pour %.2f hectares",
                            (int)(champArea * 100), champArea)
            );
        }
    }

    private void validateTreeAge(LocalDate plantingDate) {
        if (LocalDate.now().getYear() - plantingDate.getYear() > 20) {
            throw new BusinessException("Les arbres ne peuvent pas avoir plus de 20 ans");
        }
    }
}