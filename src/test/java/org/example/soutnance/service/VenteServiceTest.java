package org.example.soutnance.service;


import org.example.soutnance.repository.VenteRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class})
public class VenteServiceTest {
    @Mock
    private VenteRepository venteRepository;
    @InjectMocks
    private VenteService venteService;
}
