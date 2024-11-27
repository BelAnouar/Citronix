package org.example.soutnance.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.soutnance.domain.Fermes;
import org.example.soutnance.dto.request.FermesRequest;
import org.example.soutnance.dto.response.FermesResponse;
import org.example.soutnance.exception.BusinessException;
import org.example.soutnance.exception.ResourceNotFoundException;
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
@Slf4j
public class FermesServiceImpl implements FermeService {

    private final FermeRepository fermeRepository;
    private final FermeMapper fermeMapper;
    private static final String MESSAGE_ERROR = "Failed to create farm:";

    @Override
    public FermesResponse createFermeRepository(FermesRequest fermesRequest) {
        log.debug("Creating new farm with request: {}", fermesRequest);

        if (fermeRepository.existsByNom(fermesRequest.getNom())) {
            throw new BusinessException("Farm with name " + fermesRequest.getNom() + " already exists");
        }

        try {
            Fermes ferme = fermeMapper.toEntity(fermesRequest);
            Fermes savedFerme = fermeRepository.save(ferme);
            log.info("Successfully created farm with id: {}", savedFerme.getId());
            return fermeMapper.toResponse(savedFerme);
        } catch (Exception e) {
            log.error("Error creating farm: ", e);
            throw new BusinessException("Failed to create farm: " + e.getMessage());
        }
    }

    @Override
    public Page<FermesResponse> findAllFermes(Pageable pageable) {
        log.debug("Fetching all farms with pagination: {}", pageable);
        try {
            return fermeRepository.findAll(pageable)
                    .map(fermeMapper::toResponse);
        } catch (Exception e) {
            log.error("Error fetching farms: ", e);
            throw new BusinessException("Failed to fetch farms: " + e.getMessage());
        }
    }

    @Override
    public FermesResponse findFermeById(Long id) {
        log.debug("Fetching farm with id: {}", id);
        return fermeRepository.findById(id)
                .map(fermeMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ERROR + id));
    }

    @Override
    public void deleteFermeById(Long id) {
        log.debug("Deleting farm with id: {}", id);
        try {
            if (!fermeRepository.existsById(id)) {
                throw new ResourceNotFoundException(MESSAGE_ERROR + id);
            }

            if (fermeRepository.hasFarmAnyFields(id)) {
                throw new BusinessException("Cannot delete farm with existing fields. Please delete fields first.");
            }

            fermeRepository.deleteById(id);
            log.info("Successfully deleted farm with id: {}", id);
        } catch (ResourceNotFoundException | BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error deleting farm: ", e);
            throw new BusinessException("Failed to delete farm: " + e.getMessage());
        }
    }

    @Override
    public FermesResponse updateFermeById(Long id, FermesRequest fermesRequest) {
        log.debug("Updating farm with id: {} and request: {}", id, fermesRequest);

        Fermes existingFerme = fermeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Farm not found with id: " + id));

        if (!existingFerme.getNom().equals(fermesRequest.getNom()) &&
                fermeRepository.existsByNomAndIdNot(fermesRequest.getNom(), id)) {
            throw new BusinessException("Farm with name " + fermesRequest.getNom() + " already exists");
        }

        try {

            if (existingFerme.getSuperficieTotale() > fermesRequest.getSuperficieTotale() &&
                    fermeRepository.hasFarmAnyFields(id)) {
                double usedArea = fermeRepository.calculateTotalFieldsArea(id);
                if (fermesRequest.getSuperficieTotale() < usedArea) {
                    throw new BusinessException("Cannot reduce farm area below total area of existing fields: " + usedArea);
                }
            }

            fermeMapper.updateEntity(existingFerme, fermesRequest);
            Fermes savedFerme = fermeRepository.save(existingFerme);
            log.info("Successfully updated farm with id: {}", id);
            return fermeMapper.toResponse(savedFerme);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error updating farm: ", e);
            throw new BusinessException("Failed to update farm: " + e.getMessage());
        }
    }
}