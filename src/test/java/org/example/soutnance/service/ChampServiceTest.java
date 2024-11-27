package org.example.soutnance.service;


import org.example.soutnance.domain.Champs;
import org.example.soutnance.domain.Fermes;
import org.example.soutnance.dto.request.ChampsRequest;
import org.example.soutnance.dto.response.ChampResponse;
import org.example.soutnance.exception.ResourceNotFoundException;
import org.example.soutnance.mapper.ChampMapper;
import org.example.soutnance.repository.ChampRepository;
import org.example.soutnance.repository.FermeRepository;
import org.example.soutnance.service.impl.ChampServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class ChampServiceTest {

    @Mock
    private ChampRepository champRepository;

    @Mock
    private ChampMapper champMapper;

    @Mock
    private FermeRepository fermeRepository;

    @InjectMocks
    private ChampServiceImp champService;

    private ChampsRequest validRequest;
    private Champs validEntity;
    private ChampResponse validResponse;
    private Fermes validFarm;

    @BeforeEach
    public void setUp() {
        validRequest = ChampsRequest.builder()
                .ferme_id(1L)
                .superficie(1200.0)
                .build();

        validFarm = Fermes.builder()
                .id(1L)
                .nom("Farm A")
                .localisation("Location A")
                .superficieTotale(5000.0)
                .build();

        validEntity = Champs.builder()
                .id(1L)
                .ferme(validFarm)
                .superficie(1200.0)
                .build();

        validResponse = ChampResponse.builder()
                .id(1L)
                .superficie(1200.0)
                .build();
    }

    @Test
     void testCreateChamp_ShouldReturnResponse_WhenRequestIsValid() {

        when(fermeRepository.findById(validRequest.getFerme_id())).thenReturn(Optional.of(validFarm));
        when(champMapper.toEntity(validRequest)).thenReturn(validEntity);
        when(champRepository.save(validEntity)).thenReturn(validEntity);
        when(champMapper.toResponse(validEntity)).thenReturn(validResponse);

        ChampResponse result = champService.createChamp(validRequest);
        assertNotNull(result);
        assertEquals(validResponse.getId(), result.getId());
        assertEquals(validResponse.getSuperficie(), result.getSuperficie());
    }

    @Test
     void testCreateChamp_ShouldThrowException_WhenFarmNotFound() {

        when(fermeRepository.findById(validRequest.getFerme_id())).thenReturn(Optional.empty());


        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            champService.createChamp(validRequest);
        });

        assertEquals("Farm not found with ID: " + validRequest.getFerme_id(), exception.getMessage());
    }

    @Test
     void testGetChamp_ShouldReturnResponse_WhenFieldExists() {
        when(champRepository.findById(1L)).thenReturn(Optional.of(validEntity));
        when(champMapper.toResponse(validEntity)).thenReturn(validResponse);
        ChampResponse result = champService.getChamp(1L);
        assertNotNull(result);
        assertEquals(validResponse.getId(), result.getId());
    }

    @Test
     void testGetChamp_ShouldThrowException_WhenFieldNotFound() {
        when(champRepository.findById(1L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            champService.getChamp(1L);
        });
        assertEquals("Field not found with ID: 1", exception.getMessage());
    }

    @Test
     void testDeleteChamp_ShouldDelete_WhenFieldExists() {
        when(champRepository.existsById(1L)).thenReturn(true);
        champService.deleteChamp(1L);
       verify(champRepository).deleteById(1L);
    }

    @Test
     void testDeleteChamp_ShouldThrowException_WhenFieldNotFound() {
        when(champRepository.existsById(1L)).thenReturn(false);


        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            champService.deleteChamp(1L);
        });

        assertEquals("Field not found with ID: 1", exception.getMessage());
    }

    @Test
    void testUpdateChamp_ShouldReturnResponse_WhenFieldExistsAndRequestIsValid() {

        when(champRepository.findById(1L)).thenReturn(Optional.of(validEntity));
        when(champRepository.save(validEntity)).thenReturn(validEntity);
        when(champMapper.toResponse(validEntity)).thenReturn(validResponse);
        ChampResponse result = champService.updateChamp(1L, validRequest);
        assertNotNull(result);
        assertEquals(validResponse.getId(), result.getId());
        assertEquals(validResponse.getSuperficie(), result.getSuperficie());
        verify(champMapper).updateEntity(validEntity, validRequest);
    }


    @Test
     void testUpdateChamp_ShouldThrowException_WhenFieldNotFound() {

        when(champRepository.findById(1L)).thenReturn(Optional.empty());
         ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            champService.updateChamp(1L, validRequest);
        });

        assertEquals("Field not found with ID: 1", exception.getMessage());
    }
}
