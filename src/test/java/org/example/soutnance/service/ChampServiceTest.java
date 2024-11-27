package org.example.soutnance.service;


import org.example.soutnance.repository.ChampRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ChampServiceTest {

    @Mock
    private ChampRepository champRepository;

    @InjectMocks
    private ChampService champService;
}
