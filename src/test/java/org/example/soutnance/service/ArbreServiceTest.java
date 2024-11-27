package org.example.soutnance.service;



import org.example.soutnance.domain.Arbares;
import org.example.soutnance.domain.Champs;
import org.example.soutnance.dto.request.ArbaresRequest;
import org.example.soutnance.dto.response.ArbaresResponse;
import org.example.soutnance.exception.BusinessException;
import org.example.soutnance.exception.ResourceNotFoundException;
import org.example.soutnance.mapper.ArbreMapper;
import org.example.soutnance.repository.ArbareRepository;
import org.example.soutnance.repository.ChampRepository;
import org.example.soutnance.service.impl.ArbreServiceImpl;
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
 class ArbreServiceTest {

    @Mock
    private ArbareRepository arbareRepository;

    @Mock
    private ChampRepository champRepository;

    @Mock
    private ArbreMapper arbreMapper;

    @InjectMocks
    private ArbreServiceImpl arbreService;

    private ArbaresRequest validRequest;
    private Arbares validEntity;
    private ArbaresResponse validResponse;
    private Champs validChamp;

    @BeforeEach
    public void setUp() {
        validRequest = ArbaresRequest.builder()
                .champ_id(1L)
                .dateDeplantation(LocalDate.of(2022, Month.APRIL, 1))
                .build();

        validChamp = Champs.builder()
                .id(1L)
                .superficie(2.0)
                .build();

        validEntity = Arbares.builder()
                .id(1L)
                .dateDeplantation(validRequest.getDateDeplantation())
                .champ(validChamp)
                .build();

        validResponse = ArbaresResponse.builder()
                .id(1L)
                .dateDeplantation(validRequest.getDateDeplantation())
                .build();
    }

    @Test
     void testCreateArbre_ShouldReturnResponse_WhenRequestIsValid() {

        when(champRepository.findById(validRequest.getChamp_id())).thenReturn(Optional.of(validChamp));
        when(arbreMapper.toEntity(validRequest)).thenReturn(validEntity);
        when(arbareRepository.save(validEntity)).thenReturn(validEntity);
        when(arbreMapper.toResponse(validEntity)).thenReturn(validResponse);


        ArbaresResponse result = arbreService.createArbre(validRequest);

        assertNotNull(result);
        assertEquals(validResponse.getId(), result.getId());
        assertEquals(validResponse.getDateDeplantation(), result.getDateDeplantation());
    }

    @Test
     void testCreateArbre_ShouldThrowException_WhenChampNotFound() {

        when(champRepository.findById(validRequest.getChamp_id())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            arbreService.createArbre(validRequest);
        });

        assertEquals("Champ id" + validRequest.getChamp_id(), exception.getMessage());
    }

    @Test
     void testCreateArbre_ShouldThrowException_WhenInvalidPlantingPeriod() {

        validRequest.setDateDeplantation(LocalDate.of(2022, Month.JANUARY, 1));
        when(champRepository.findById(validRequest.getChamp_id())).thenReturn(Optional.of(validChamp));

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            arbreService.createArbre(validRequest);
        });

        assertEquals("Les arbres ne peuvent être plantés qu'entre mars et mai", exception.getMessage());
    }

    @Test
     void testGetArbre_ShouldReturnResponse_WhenArbreExists() {

        when(arbareRepository.findById(1L)).thenReturn(Optional.of(validEntity));
        when(arbreMapper.toResponse(validEntity)).thenReturn(validResponse);


        ArbaresResponse result = arbreService.getArbre(1L);

        assertNotNull(result);
        assertEquals(validResponse.getId(), result.getId());
    }

    @Test
     void testGetArbre_ShouldThrowException_WhenArbreNotFound() {

        when(arbareRepository.findById(1L)).thenReturn(Optional.empty());


        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            arbreService.getArbre(1L);
        });

        assertEquals("Arbre id1", exception.getMessage());
    }

    @Test
     void testUpdateArbre_ShouldReturnResponse_WhenArbreExistsAndRequestIsValid() {

        when(arbareRepository.findById(1L)).thenReturn(Optional.of(validEntity));
        when(arbareRepository.save(validEntity)).thenReturn(validEntity);
        when(arbreMapper.toResponse(validEntity)).thenReturn(validResponse);
        ArbaresResponse result = arbreService.updateArbre(1L, validRequest);
        assertNotNull(result);
        assertEquals(validResponse.getId(), result.getId());
        assertEquals(validResponse.getDateDeplantation(), result.getDateDeplantation());


        verify(arbreMapper).updateEntity(validEntity, validRequest);
    }

    @Test
     void testUpdateArbre_ShouldThrowException_WhenArbreNotFound() {

        when(arbareRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            arbreService.updateArbre(1L, validRequest);
        });

        assertEquals("Arbre id1", exception.getMessage());
    }

    @Test
     void testUpdateArbre_ShouldThrowException_WhenInvalidPlantingPeriod() {

        validRequest.setDateDeplantation(LocalDate.of(2022, Month.JANUARY, 1));

        when(arbareRepository.findById(1L)).thenReturn(Optional.of(validEntity));


        BusinessException exception = assertThrows(BusinessException.class, () -> {
            arbreService.updateArbre(1L, validRequest);
        });

        assertEquals("Les arbres ne peuvent être plantés qu'entre mars et mai", exception.getMessage());
    }
}
