package org.example.soutnance.service;


import org.example.soutnance.domain.Champs;
import org.example.soutnance.domain.Recoltes;
import org.example.soutnance.domain.enums.Saison;
import org.example.soutnance.dto.request.RecoltesRequest;
import org.example.soutnance.dto.response.RecoltesResponse;
import org.example.soutnance.exception.BusinessException;
import org.example.soutnance.exception.ResourceNotFoundException;
import org.example.soutnance.mapper.RecolteMapper;
import org.example.soutnance.repository.ChampRepository;
import org.example.soutnance.repository.RecolteRepository;
import org.example.soutnance.service.impl.RecolteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class RecolteServiceTest {

    @Mock
    private RecolteRepository recolteRepository;

    @Mock
    private ChampRepository champRepository;

    @Mock
    private RecolteMapper recolteMapper;

    @InjectMocks
    private RecolteServiceImpl recolteService;

    private RecoltesRequest validRequest;
    private Recoltes validEntity;
    private RecoltesResponse validResponse;
    private Champs validChamp;

    @BeforeEach
    public void setUp() {
        validRequest = RecoltesRequest.builder()
                .champ_id(1L)
                .saison(Saison.ETE)
                .dateRecolte(LocalDate.of(2023, Month.JUNE, 15))
                .build();

        validChamp = Champs.builder()
                .id(1L)
                .superficie(2.0)
                .build();

        validEntity = Recoltes.builder()
                .id(1L)
                .champ(validChamp)
                .saison(Saison.ETE)
                .dateRecolte(validRequest.getDateRecolte())
                .quantiteTotale(0.0)
                .build();

        validResponse = RecoltesResponse.builder()
                .id(1L)
                .saison(Saison.ETE)
                .dateRecolte(validRequest.getDateRecolte())
                .quantite_totale(0.0)
                .build();
    }

    @Test
     void testCreateRecolte_ShouldReturnResponse_WhenRequestIsValid() {

        when(champRepository.findById(validRequest.getChamp_id())).thenReturn(Optional.of(validChamp));
        when(recolteRepository.existsByChamp_IdAndSaison(validRequest.getChamp_id(), validRequest.getSaison())).thenReturn(false);
        when(recolteMapper.toEntity(validRequest)).thenReturn(validEntity);
        when(recolteRepository.save(validEntity)).thenReturn(validEntity);
        when(recolteMapper.toResponse(validEntity)).thenReturn(validResponse);
        RecoltesResponse result = recolteService.createRecolte(validRequest);
        assertNotNull(result);
        assertEquals(validResponse.getId(), result.getId());
        assertEquals(validResponse.getSaison(), result.getSaison());
        assertEquals(validResponse.getDateRecolte(), result.getDateRecolte());
    }

    @Test
     void testCreateRecolte_ShouldThrowException_WhenChampNotFound() {
        when(champRepository.findById(validRequest.getChamp_id())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            recolteService.createRecolte(validRequest);
        });

        assertEquals("Champ not found with id: " + validRequest.getChamp_id(), exception.getMessage());
    }

    @Test
     void testCreateRecolte_ShouldThrowException_WhenHarvestAlreadyExists() {
        when(champRepository.findById(validRequest.getChamp_id())).thenReturn(Optional.of(validChamp));
        when(recolteRepository.existsByChamp_IdAndSaison(validRequest.getChamp_id(), validRequest.getSaison())).thenReturn(true);
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            recolteService.createRecolte(validRequest);
        });

        assertEquals("A harvest already exists for this field in the specified season", exception.getMessage());
    }

    @Test
     void testGetRecolte_ShouldReturnResponse_WhenRecolteExists() {
        when(recolteRepository.findById(1L)).thenReturn(Optional.of(validEntity));
        when(recolteMapper.toResponse(validEntity)).thenReturn(validResponse);
        RecoltesResponse result = recolteService.getRecolte(1L);

        assertNotNull(result);
        assertEquals(validResponse.getId(), result.getId());
        assertEquals(validResponse.getSaison(), result.getSaison());
    }

    @Test
     void testGetRecolte_ShouldThrowException_WhenRecolteNotFound() {
        when(recolteRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            recolteService.getRecolte(1L);
        });

        assertEquals("Recolte not found with id: 1", exception.getMessage());
    }
    @Test
     void testUpdateRecolte_ShouldReturnResponse_WhenRecolteExistsAndRequestIsValid() {

        when(recolteRepository.findById(1L)).thenReturn(Optional.of(validEntity));
        when(recolteRepository.save(validEntity)).thenReturn(validEntity); // Mock save to return the modified entity
        when(recolteMapper.toResponse(validEntity)).thenReturn(validResponse); // Map the modified entity to the response

        RecoltesResponse result = recolteService.updateRecolte(1L, validRequest);

        assertNotNull(result);
        assertEquals(validResponse.getId(), result.getId());
        assertEquals(validResponse.getSaison(), result.getSaison());
        assertEquals(validResponse.getDateRecolte(), result.getDateRecolte());

        verify(recolteMapper).updateEntity(validEntity, validRequest);
    }



    @Test
     void testUpdateRecolte_ShouldThrowException_WhenRecolteNotFound() {
        when(recolteRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            recolteService.updateRecolte(1L, validRequest);
        });

        assertEquals("Recolte not found with id: 1", exception.getMessage());
    }

    @Test
     void testDeleteRecolte_ShouldCallDelete_WhenRecolteExists() {

        when(recolteRepository.existsById(1L)).thenReturn(true);

        recolteService.deleteRecolte(1L);

        verify(recolteRepository).deleteById(1L);

    }

    @Test
     void testDeleteRecolte_ShouldThrowException_WhenRecolteNotFound() {

        when(recolteRepository.existsById(1L)).thenReturn(false);


        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            recolteService.deleteRecolte(1L);
        });

        assertEquals("Recolte not found with id: 1", exception.getMessage());
    }
}
