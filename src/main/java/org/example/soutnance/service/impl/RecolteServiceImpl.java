package org.example.soutnance.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.soutnance.domain.enums.Saison;
import org.example.soutnance.dto.request.RecoltesRequest;
import org.example.soutnance.dto.response.RecoltesResponse;
import org.example.soutnance.exception.BusinessException;
import org.example.soutnance.exception.ResourceNotFoundException;
import org.example.soutnance.mapper.RecolteMapper;
import org.example.soutnance.repository.ChampRepository;
import org.example.soutnance.repository.RecolteRepository;
import org.example.soutnance.service.RecolteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class RecolteServiceImpl implements RecolteService {
    private final RecolteRepository recolteRepository;
    private final ChampRepository champRepository;
    private final RecolteMapper recolteMapper;


    @Override
    public RecoltesResponse createRecolte(RecoltesRequest request) {

        var champ = champRepository.findById(request.getChamp_id())
                .orElseThrow(() -> new ResourceNotFoundException("Champ not found with id: " + request.getChamp_id()));


        validateSeasonConstraints(champ.getId() ,request.getSaison());


        var recolte = recolteMapper.toEntity(request);
        recolte = recolteRepository.save(recolte);

        return recolteMapper.toResponse(recolte);
    }

    @Override
    public Page<RecoltesResponse> getRecoltes(Pageable pageable) {
        return recolteRepository.findAll(pageable)
                .map(recolteMapper::toResponse);
    }

    @Override
    public RecoltesResponse getRecolte(Long id) {
        return recolteRepository.findById(id)
                .map(recolteMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Recolte not found with id: " + id));
    }

    @Override
    public RecoltesResponse updateRecolte(Long id, RecoltesRequest request) {



        var recolte = recolteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recolte not found with id: " + id));


        if (!recolte.getChamp().getId().equals(request.getChamp_id())) {
            champRepository.findById(request.getChamp_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Champ not found with id: " + request.getChamp_id()));
            validateSeasonConstraints(request.getChamp_id(), request.getSaison());
        }

        recolteMapper.updateEntity(recolte, request);
        recolte = recolteRepository.save(recolte);

        return recolteMapper.toResponse(recolte);
    }

    @Override
    public void deleteRecolte(Long id) {
        if (!recolteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recolte not found with id: " + id);
        }
        recolteRepository.deleteById(id);
    }

    private void validateSeasonConstraints(Long champId, Saison saison) {
        boolean exists = recolteRepository.existsByChamp_IdAndSaison(champId, saison);
        if (exists) {
            throw new BusinessException("A harvest already exists for this field in the specified season");
        }
    }
}
