package org.example.soutnance.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.soutnance.domain.DetailRecoltes;
import org.example.soutnance.domain.Recoltes;
import org.example.soutnance.domain.enums.Saison;
import org.example.soutnance.dto.request.RecolteDetailRequest;
import org.example.soutnance.dto.response.RecolteDetailResponse;
import org.example.soutnance.exception.BusinessException;
import org.example.soutnance.exception.ResourceNotFoundException;
import org.example.soutnance.mapper.RecolteDetailsMapper;
import org.example.soutnance.repository.ArbareRepository;
import org.example.soutnance.repository.DetailRecolte;
import org.example.soutnance.repository.RecolteRepository;
import org.example.soutnance.service.RecolteDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecolteDetailsImpl implements RecolteDetails {



    private final DetailRecolte recolteDetailRepository;
    private final RecolteRepository recolteRepository;
    private final ArbareRepository arbreRepository;
    private final RecolteDetailsMapper recolteDetailMapper;

    @Override
    public RecolteDetailResponse createRecolteDetail( RecolteDetailRequest request) {
        var recolte = recolteRepository.findById(request.getRecolte_id())
                .orElseThrow(() -> new ResourceNotFoundException("Recolte not found with id: " + request.getRecolte_id()));

        var arbre = arbreRepository.findById(request.getArbre_id())
                .orElseThrow(() -> new ResourceNotFoundException("Arbre not found with id: " + request.getArbre_id()));


        validateTreeHarvest(arbre.getId(), recolte.getSaison());


        if (!arbre.getChamp().getId().equals(recolte.getChamp().getId())) {
            throw new BusinessException("Tree does not belong to the harvest's field");
        }

        var recolteDetail = recolteDetailMapper.toEntity(request);
        recolteDetail.setRecolte(recolte);
        recolteDetail = recolteDetailRepository.save(recolteDetail);


        recolte.setQuantiteTotale(recolte.getQuantiteTotale() + request.getQuantite());

        recolteRepository.save(recolte);

        return recolteDetailMapper.toResponse(recolteDetail);
    }

    @Override
    public Page<RecolteDetailResponse> getAllRecolteDetails(Pageable pageable) {
        return recolteDetailRepository.findAll(pageable).map(recolteDetailMapper::toResponse);
    }

    @Override
    public RecolteDetailResponse getRecolteDetails(Long id) {
        DetailRecoltes recoltes = recolteDetailRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("not found"));
        return recolteDetailMapper.toResponse(recoltes);
    }



    @Override
    public void deleteRecolteDetails(Long id) {
          recolteRepository.deleteById(id);
    }

    private void validateTreeHarvest(Long arbreId, Saison saison) {
        boolean harvested = recolteDetailRepository
                .existsByArbre_IdAndRecolte_Saison(arbreId, saison);
        if (harvested) {
            throw new BusinessException("Tree has already been harvested this season");
        }
    }
}




