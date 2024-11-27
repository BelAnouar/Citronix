package org.example.soutnance.service;



import org.example.soutnance.domain.Fermes;
import org.example.soutnance.dto.request.FermesRequest;
import org.example.soutnance.dto.response.FermesResponse;
import org.example.soutnance.exception.BusinessException;
import org.example.soutnance.mapper.FermeMapper;
import org.example.soutnance.repository.FermeRepository;
import org.example.soutnance.service.impl.FermesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;



import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FermesServiceImplTest {

    @Mock
    private FermeRepository fermeRepository;

    @Mock
    private FermeMapper fermeMapper;

    @InjectMocks
    private FermesServiceImpl fermesService;

    private FermesRequest validRequest;
    private Fermes validEntity;
    private FermesResponse validResponse;

    @BeforeEach
    public void setUp() {
        validRequest = FermesRequest.builder()
                .nom("Farm A")
                .localisation("Location A")
                .superficieTotale(1500.0)
                .dateCreation(LocalDate.of(2023, 1, 1))
                .build();

        validEntity = Fermes.builder()
                .id(1L)
                .nom("Farm A")
                .localisation("Location A")
                .superficieTotale(1500.0)
                .dateCreation(LocalDate.of(2023, 1, 1))
                .build();

        validResponse = FermesResponse.builder()
                .id(1L)
                .nom("Farm A")
                .localisation("Location A")
                .superficieTotale(1500.0)
                .dateCreation(LocalDate.of(2023, 1, 1))
                .build();
    }

    @Test
     void testCreateFermeRepository_ShouldReturnResponse_WhenRequestIsValid() {

        when(fermeRepository.existsByNom(validRequest.getNom())).thenReturn(false);
        when(fermeMapper.toEntity(validRequest)).thenReturn(validEntity);
        when(fermeRepository.save(validEntity)).thenReturn(validEntity);
        when(fermeMapper.toResponse(validEntity)).thenReturn(validResponse);

        FermesResponse result = fermesService.createFermeRepository(validRequest);


        assertNotNull(result);
        assertEquals(validResponse.getId(), result.getId());
        assertEquals(validResponse.getNom(), result.getNom());
        assertEquals(validResponse.getLocalisation(), result.getLocalisation());
        assertEquals(validResponse.getSuperficieTotale(), result.getSuperficieTotale(), 0.01);
    }

    @Test
     void testCreateFermeRepository_ShouldThrowException_WhenFarmWithNameAlreadyExists() {

        when(fermeRepository.existsByNom(validRequest.getNom())).thenReturn(true);
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            fermesService.createFermeRepository(validRequest);
        });

        assertEquals("Farm with name " + validRequest.getNom() + " already exists", exception.getMessage());
    }

    @Test
     void testCreateFermeRepository_ShouldThrowException_WhenSaveFails() {
        when(fermeRepository.existsByNom(validRequest.getNom())).thenReturn(false);
        when(fermeMapper.toEntity(validRequest)).thenReturn(validEntity);
        when(fermeRepository.save(validEntity)).thenThrow(new RuntimeException("Database error"));
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            fermesService.createFermeRepository(validRequest);
        });
        assertEquals("Failed to create farm: Database error", exception.getMessage());
    }
}
